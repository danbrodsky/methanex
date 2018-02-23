import { directive as vClickOutside } from 'vue-clickaway'

/**
 * You can register global directives here and use them as a plugin in your main Vue instance
 */

const GlobalDirectives = {
  install (Vue) {
    Vue.directive('click-outside', vClickOutside)
    Vue.directive('sticky-to-top', { 
      inserted: function (el, binding, vnode) {

        var position = el.offsetTop;
        var crossed = false;
        
        window.addEventListener('scroll', function (e) {
          if(!crossed && window.pageYOffset > position){
            el.style.position = "fixed";
            el.style.top = 0;
            el.style.width = el.parentElement.clientWidth + "px"; //this is bad 
            el.style.zIndex = 1;
            // position = position - el.clientHeight;
            window.scrollTo(0,position - el.clientHeight);
            crossed = true;
          }
          else if (crossed && window.pageYOffset < position - el.clientHeight){
            // el.removeAttribute("position");
            // el.removeAttribute("top");
            // el.removeAttribute("zIndex");
            el.style.position = "static";
            window.scrollTo(0,position);
            crossed = false;
          }
        })
      }
    })
  }
}

export default GlobalDirectives
