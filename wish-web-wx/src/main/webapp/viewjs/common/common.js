/**
 * 公共方法: 模板编译
 * @param segment{$HTMLElement} jquery dom 对象
 * @param data{Object} 数据模型对象
 * @return {$HTMLElement} 编译后的$dom对象
 * Usage----->
 * 在构造函数中
 * compile.call(this, $(htmlString), this.viewModel)
 * */
function templateCompile(segment, data, parentData, $index) {
  var self = this;
  var watchList = [];
  var $excludeElement = segment.find('[data-bind*=foreach] [data-bind]');
  //segment.find('[data-bind]').filter(function (i, e) {
  //  return !parentsHasProperty($(e), 'data-bind', 'foreach');
  //});
  segment.find('[data-bind]').each(function (index, element) {
    var $elem = $(element);
    var expression = $elem.attr('data-bind').replace(/\$root/g, 'self.viewModel');
    var matchs = expression.match(/^\b(\w*?)\b:(.*)/);
    var dataAttrNames = ['data-if', 'data-href', 'data-attr', 'data-bind', 'data-class'];
    if (elemIsInForeach($elem, $excludeElement)) {
      return;
    }
    if (typeof data === 'array') {
      data = {_arr_: data};
    }
    with (data) {
      var ifAttr = $elem.attr(dataAttrNames[0]);
      var hrefAttr = $elem.attr(dataAttrNames[1]);
      var attrData = $elem.attr(dataAttrNames[2]);
      var $parent = parentData;
      if (ifAttr) {
        ifAttr = ifAttr.replace(/\$root/g, 'self.viewModel');
        if (!eval(ifAttr)) {
          $elem.remove();
          return;
        }
      }
      if(hrefAttr){
        hrefAttr = hrefAttr.replace(/\$root/g, 'self.viewModel');
        hrefAttr = eval(hrefAttr);
        $elem.attr('href', hrefAttr);
      }
      var classAttr = $elem.attr(dataAttrNames[4]);
      if (classAttr) {
        classAttr = classAttr.replace(/\$root/g, 'self.viewModel');
        classAttr = eval(classAttr);
        $elem.addClass(classAttr);
      }
      if(attrData){
        var attrMatchs;
        eval('attrMatchs=' + attrData);
        for(var _name in attrMatchs){
          var _val = attrMatchs[_name];
          if(_name === 'addClass'){
            $elem.addClass(_val);
          }else{
            $elem.attr(_name, _val);
          }
        }
      }
      if(matchs){
      switch (matchs[1]) {
        case 'text':
        if (matchs[2] === '[]') {
          $elem.text(data);
        } else {
          $elem.text(eval(matchs[2]));
          watchList.push({
            key: matchs[2],
            Func: function (newVal) {
              $elem.text(newVal);
            }
          });
        }
        break;
        case 'html':
            $elem.html(eval(matchs[2]));
            watchList.push({
              key: matchs[2],
              Func: function (newVal) {
                $elem.html(newVal);
              }
            });
          break;
        case 'foreach':
          var eachData = eval(matchs[2]);
          var listFragment = document.createDocumentFragment();
          if (eachData instanceof Array) {
            for (var i = 0, len = eachData.length; i < len; i++) {
              var $listElement, $foreachElement;
              $listElement= $elem.clone();
              $foreachElement = self.compile($listElement, eachData[i], eachData, i);
              $foreachElement.children().each(function (index, element) {
                //var e = $(element).clone()[0];
                listFragment.appendChild(element);
              });
            }
          }
          $elem.html('');
          $elem.append(listFragment);
          break;
        case 'click':
          $elem[0].onclick = (function($index){
            return function (event) {
              eval(matchs[2]).call(this, data, event, $index);
            }
          })($index);
          break;
        case 'attr':
          //todo:attr:{a:'t1', b:'t2'}
          var attrMatchs = matchs[2].match(/{(\w.*?):(.*)}/);
          $elem.attr(attrMatchs[1], eval(attrMatchs[2]));
          var match2, watchStr;
          if ((match2 = /^([\w\.]+)\b.*?\?.*:/.exec(attrMatchs[2])) !== null) {
            watchStr = match2[1];
          }
          watchList.push({
            key: watchStr,
            Func: function (newVal) {
              $elem.attr(attrMatchs[1], eval(attrMatchs[2]));
            }
          });
          break;
      }
      }
    }
    dataAttrNames.forEach(function(e){
      $elem.removeAttr(e);
    });
  });
  watch.call(self, watchList, data, self.viewModel);
  return segment;

  function elemIsInForeach($elem, $excludeElement) {
    var result = false;
    $excludeElement.each(function (index, elem) {
      if (elem === $elem[0]) {
        result = true;
      }
    });
    return result;
  }

  function parentsHasProperty(element, proName, proValue) {
    var parent = element.parent();
    var _proName = proName;
    var reg = new RegExp('\\b' + proValue + '\\b');
    var isHas = false;
    element.parents().each(function () {
      var proName = $(this).attr(_proName);
      if (typeof proName !== 'undefined' && reg.test(proValue)) {
        isHas = true;
      }
    });
    return isHas;
  }

  function watch(watchList, obj, modelRoot) {
    var self = this;
    for (var i = 0, item, arr = []; i < watchList.length; i++) {
      item = watchList[i];
      item.Func = [item.Func];
      for (var n = 0, item2, isRepeat = false; n < arr.length; n++) {
        item2 = arr[n];
        if (item.key === item2.key) {
          item2.Func = item2.Func.concat(item.Func);
          isRepeat = true;
        }
      }
      !isRepeat && arr.push(item);
    }
    watchList = arr;
    watchList.forEach(function (item) {
      var watchStr = item.key, value, watchHandler, watchObj, match;
      var defaultSetFunc = function (newVal) {
        var oldVal = value;
        value = newVal;
        if (watchHandler instanceof Array) {
          watchHandler.forEach(function (handler) {
            handler.call(self, newVal, oldVal);
          });
        } else {
          watchHandler.call(self, newVal, oldVal);
        }
      };
      if ((match = /self.viewModel.(.*)/.exec(watchStr))) {
        watchObj = modelRoot;
        watchStr = match[1];
      } else {
        watchObj = obj;
      }
      value = watchObj[watchStr];
      (function (scope) {
        watchHandler = typeof item.Func !== 'string' ? item.Func : eval(item.Func);
      })(obj);

      Object.defineProperty(watchObj, watchStr, {
        get: function () {
          return value;
        },
        set: defaultSetFunc
      });
    });
  }
}

/**
 * 组件基类
 * @convertModel{function}[optional] 转换数据源方法
 * @dataModel{object}[optional] 视图层的数据模型
 * @templateSelector{string}[required] 指定html模板在哪
 * @container{string}[optional] 指定渲染的容器
 * @onEmpty{fuction}[optional] 当空数据的时候
 * */
function Component( convertModel, dataModel, templateSelector, container, onEmpty) {
  var resData;
  function isEmptyObj(obj){
    var n = 0;
    for(var name in obj){
      n++;
    }
    return n === 0;
  }
  try {
    eval('resData = ' + $('[name=responseData]').val());
  } catch (ex) {
    alert('数据有误!');
    return;
  } finally {
    if ((typeof resData === 'array' && resData.length === 0) || (typeof resData === 'object' && isEmptyObj(resData))) {
      console.log('没有数据!');
      //return;
    }
  }
  var template = $(templateSelector || '#template').html();
  if (convertModel) {
    convertModel(resData);
  }
  this.viewModel = dataModel || {resData: resData};
  this.compile = templateCompile;
  var segment = this.compile($(template), this.viewModel);
  segment.appendTo(container || 'body');
}

function alerttip(msg){
  if(msg.length>=16){
    var html='<div class="mytip">'+msg+'</div>'
  }else{
    var html='<div class="mytip">'+msg+'</div>'
  }
  $(html).appendTo("body");
  var w=$(".mytip").width();
  $(".mytip").animate({opacity:"0.5"},500);
  timer2=setTimeout(hide,2200);
}
function hide(){
  $(".mytip").animate({opacity:"0"},500,function(){
    $(".mytip").remove();
  });
}


//表单字段验证器
var validator = {
  init: function(formSelector){
    $(formSelector || 'form').find('[data-rule]').each(function (i, e) {
      this.onblur = validator.checkRule;
    });
    document.body.addEventListener('click',function(event){
      var target = event.target;
      var hasError = false;
      if($(target).hasClass('submit')){
        $(formSelector || 'form').find('[data-rule]').each(function (i, e) {
          if(validator.checkRule.call(this)){
            hasError = true;
          };
        });
      }
      if(hasError){
        event.preventDefault();
        event.stopPropagation();
        return false;
      }
    },true);
  },
  checkRule: function () {
    var msg = validator.getError.call(this);
    if (msg) {
      validator.removeErrorMsg.call(this);
      validator.addErrorMsg.call(this, msg);
    } else {
      validator.removeErrorMsg.call(this);
    }
    return msg ? true : false;
  },
  getError: function () {
    var rules = {
      mobile: {
        reg: '(\\+86)\?1[3,5,6,7,8]\\d{9}',
        errorMsg: '请输入正确的手机号码'
      },
      verification: {
        reg: '\\d{6}',
        errorMsg: '请输入正确的验证码'
      }
    };
    var thisRule = rules[$(this).attr('data-rule')];
    var reg = new RegExp('^' + thisRule.reg + '$');
    if (!reg.test($(this).val())) {
      return thisRule.errorMsg;
    }
  },
  removeErrorMsg: function () {
    $(this).parent().next('.error-message').remove();
  },
  addErrorMsg: function (msg) {
    $('<b class="error-message" style="display: block">' + msg + '</b>').insertAfter($(this).parent());
  }
};