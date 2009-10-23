/*
 * Ext JS Library 3.0.0
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.Button=Ext.extend(Ext.BoxComponent,{hidden:false,disabled:false,pressed:false,enableToggle:false,menuAlign:"tl-bl?",type:"button",menuClassTarget:"tr:nth(2)",clickEvent:"click",handleMouseEvents:true,tooltipType:"qtip",buttonSelector:"button:first-child",scale:"small",iconAlign:"left",arrowAlign:"right",initComponent:function(){Ext.Button.superclass.initComponent.call(this);this.addEvents("click","toggle","mouseover","mouseout","menushow","menuhide","menutriggerover","menutriggerout");if(this.menu){this.menu=Ext.menu.MenuMgr.get(this.menu)}if(Ext.isString(this.toggleGroup)){this.enableToggle=true}},getTemplateArgs:function(){var a=(this.cls||"");a+=(this.iconCls||this.icon)?(this.text?" x-btn-text-icon":" x-btn-icon"):" x-btn-noicon";if(this.pressed){a+=" x-btn-pressed"}return[this.text||"&#160;",this.type,this.iconCls||"",a,"x-btn-"+this.scale+" x-btn-icon-"+this.scale+"-"+this.iconAlign,this.getMenuClass()]},getMenuClass:function(){return this.menu?(this.arrowAlign!="bottom"?"x-btn-arrow":"x-btn-arrow-bottom"):""},onRender:function(c,a){if(!this.template){if(!Ext.Button.buttonTemplate){Ext.Button.buttonTemplate=new Ext.Template('<table cellspacing="0" class="x-btn {3}"><tbody class="{4}">','<tr><td class="x-btn-tl"><i>&#160;</i></td><td class="x-btn-tc"></td><td class="x-btn-tr"><i>&#160;</i></td></tr>','<tr><td class="x-btn-ml"><i>&#160;</i></td><td class="x-btn-mc"><em class="{5}" unselectable="on"><button class="x-btn-text {2}" type="{1}">{0}</button></em></td><td class="x-btn-mr"><i>&#160;</i></td></tr>','<tr><td class="x-btn-bl"><i>&#160;</i></td><td class="x-btn-bc"></td><td class="x-btn-br"><i>&#160;</i></td></tr>',"</tbody></table>");Ext.Button.buttonTemplate.compile()}this.template=Ext.Button.buttonTemplate}var b,d=this.getTemplateArgs();if(a){b=this.template.insertBefore(a,d,true)}else{b=this.template.append(c,d,true)}this.btnEl=b.child(this.buttonSelector);this.mon(this.btnEl,{scope:this,focus:this.onFocus,blur:this.onBlur});this.initButtonEl(b,this.btnEl);Ext.ButtonToggleMgr.register(this)},initButtonEl:function(b,c){this.el=b;if(this.id){this.el.dom.id=this.el.id=this.id}if(this.icon){c.setStyle("background-image","url("+this.icon+")")}if(this.tabIndex!==undefined){c.dom.tabIndex=this.tabIndex}if(this.tooltip){this.setTooltip(this.tooltip,true)}if(this.handleMouseEvents){this.mon(b,{scope:this,mouseover:this.onMouseOver,mousedown:this.onMouseDown})}if(this.menu){this.mon(this.menu,{scope:this,show:this.onMenuShow,hide:this.onMenuHide})}if(this.repeat){var a=new Ext.util.ClickRepeater(b,Ext.isObject(this.repeat)?this.repeat:{});this.mon(a,"click",this.onClick,this)}this.mon(b,this.clickEvent,this.onClick,this)},afterRender:function(){Ext.Button.superclass.afterRender.call(this);this.doAutoWidth()},setIconClass:function(a){if(this.el){this.btnEl.replaceClass(this.iconCls,a)}this.iconCls=a;return this},setTooltip:function(b,a){if(this.rendered){if(!a){this.clearTip()}if(Ext.isObject(b)){Ext.QuickTips.register(Ext.apply({target:this.btnEl.id},b));this.tooltip=b}else{this.btnEl.dom[this.tooltipType]=b}}else{this.tooltip=b}return this},clearTip:function(){if(Ext.isObject(this.tooltip)){Ext.QuickTips.unregister(this.btnEl)}},beforeDestroy:function(){if(this.rendered){this.clearTip()}Ext.destroy(this.menu,this.repeater)},onDestroy:function(){var a=Ext.getDoc();a.un("mouseover",this.monitorMouseOver,this);a.un("mouseup",this.onMouseUp,this);if(this.rendered){Ext.ButtonToggleMgr.unregister(this)}},doAutoWidth:function(){if(this.el&&this.text&&this.width===undefined){this.el.setWidth("auto");if(Ext.isIE7&&Ext.isStrict){var a=this.btnEl;if(a&&a.getWidth()>20){a.clip();a.setWidth(Ext.util.TextMetrics.measure(a,this.text).width+a.getFrameWidth("lr"))}}if(this.minWidth){if(this.el.getWidth()<this.minWidth){this.el.setWidth(this.minWidth)}}}},setHandler:function(b,a){this.handler=b;this.scope=a;return this},setText:function(a){this.text=a;if(this.el){this.el.child("td.x-btn-mc "+this.buttonSelector).update(a)}this.doAutoWidth();return this},getText:function(){return this.text},toggle:function(b,a){b=b===undefined?!this.pressed:!!b;if(b!=this.pressed){this.el[b?"addClass":"removeClass"]("x-btn-pressed");this.pressed=b;if(!a){this.fireEvent("toggle",this,b);if(this.toggleHandler){this.toggleHandler.call(this.scope||this,this,b)}}}return this},focus:function(){this.btnEl.focus()},onDisable:function(){this.onDisableChange(true)},onEnable:function(){this.onDisableChange(false)},onDisableChange:function(a){if(this.el){if(!Ext.isIE6||!this.text){this.el[a?"addClass":"removeClass"](this.disabledClass)}this.el.dom.disabled=a}this.disabled=a},showMenu:function(){if(this.rendered&&this.menu){if(this.tooltip){Ext.QuickTips.getQuickTip().cancelShow(this.btnEl)}this.menu.show(this.el,this.menuAlign)}return this},hideMenu:function(){if(this.menu){this.menu.hide()}return this},hasVisibleMenu:function(){return this.menu&&this.menu.isVisible()},onClick:function(a){if(a){a.preventDefault()}if(a.button!==0){return}if(!this.disabled){if(this.enableToggle&&(this.allowDepress!==false||!this.pressed)){this.toggle()}if(this.menu&&!this.menu.isVisible()&&!this.ignoreNextClick){this.showMenu()}this.fireEvent("click",this,a);if(this.handler){this.handler.call(this.scope||this,this,a)}}},isMenuTriggerOver:function(b,a){return this.menu&&!a},isMenuTriggerOut:function(b,a){return this.menu&&!a},onMouseOver:function(b){if(!this.disabled){var a=b.within(this.el,true);if(!a){this.el.addClass("x-btn-over");if(!this.monitoringMouseOver){Ext.getDoc().on("mouseover",this.monitorMouseOver,this);this.monitoringMouseOver=true}this.fireEvent("mouseover",this,b)}if(this.isMenuTriggerOver(b,a)){this.fireEvent("menutriggerover",this,this.menu,b)}}},monitorMouseOver:function(a){if(a.target!=this.el.dom&&!a.within(this.el)){if(this.monitoringMouseOver){Ext.getDoc().un("mouseover",this.monitorMouseOver,this);this.monitoringMouseOver=false}this.onMouseOut(a)}},onMouseOut:function(b){var a=b.within(this.el)&&b.target!=this.el.dom;this.el.removeClass("x-btn-over");this.fireEvent("mouseout",this,b);if(this.isMenuTriggerOut(b,a)){this.fireEvent("menutriggerout",this,this.menu,b)}},onFocus:function(a){if(!this.disabled){this.el.addClass("x-btn-focus")}},onBlur:function(a){this.el.removeClass("x-btn-focus")},getClickEl:function(b,a){return this.el},onMouseDown:function(a){if(!this.disabled&&a.button===0){this.getClickEl(a).addClass("x-btn-click");Ext.getDoc().on("mouseup",this.onMouseUp,this)}},onMouseUp:function(a){if(a.button===0){this.getClickEl(a,true).removeClass("x-btn-click");Ext.getDoc().un("mouseup",this.onMouseUp,this)}},onMenuShow:function(a){this.ignoreNextClick=0;this.el.addClass("x-btn-menu-active");this.fireEvent("menushow",this,this.menu)},onMenuHide:function(a){this.el.removeClass("x-btn-menu-active");this.ignoreNextClick=this.restoreClick.defer(250,this);this.fireEvent("menuhide",this,this.menu)},restoreClick:function(){this.ignoreNextClick=0}});Ext.reg("button",Ext.Button);Ext.ButtonToggleMgr=function(){var a={};function b(e,h){if(h){var f=a[e.toggleGroup];for(var d=0,c=f.length;d<c;d++){if(f[d]!=e){f[d].toggle(false)}}}}return{register:function(c){if(!c.toggleGroup){return}var d=a[c.toggleGroup];if(!d){d=a[c.toggleGroup]=[]}d.push(c);c.on("toggle",b)},unregister:function(c){if(!c.toggleGroup){return}var d=a[c.toggleGroup];if(d){d.remove(c);c.un("toggle",b)}},getPressed:function(f){var e=a[f];if(e){for(var d=0,c=e.length;d<c;d++){if(e[d].pressed===true){return e[d]}}}return null}}}();Ext.SplitButton=Ext.extend(Ext.Button,{arrowSelector:"em",split:true,initComponent:function(){Ext.SplitButton.superclass.initComponent.call(this);this.addEvents("arrowclick")},onRender:function(){Ext.SplitButton.superclass.onRender.apply(this,arguments);if(this.arrowTooltip){this.el.child(this.arrowSelector).dom[this.tooltipType]=this.arrowTooltip}},setArrowHandler:function(b,a){this.arrowHandler=b;this.scope=a},getMenuClass:function(){return"x-btn-split"+(this.arrowAlign=="bottom"?"-bottom":"")},isClickOnArrow:function(a){return this.arrowAlign!="bottom"?a.getPageX()>this.el.child(this.buttonSelector).getRegion().right:a.getPageY()>this.el.child(this.buttonSelector).getRegion().bottom},onClick:function(b,a){b.preventDefault();if(!this.disabled){if(this.isClickOnArrow(b)){if(this.menu&&!this.menu.isVisible()&&!this.ignoreNextClick){this.showMenu()}this.fireEvent("arrowclick",this,b);if(this.arrowHandler){this.arrowHandler.call(this.scope||this,this,b)}}else{if(this.enableToggle){this.toggle()}this.fireEvent("click",this,b);if(this.handler){this.handler.call(this.scope||this,this,b)}}}},isMenuTriggerOver:function(a){return this.menu&&a.target.tagName=="em"},isMenuTriggerOut:function(b,a){return this.menu&&b.target.tagName!="em"}});Ext.reg("splitbutton",Ext.SplitButton);Ext.CycleButton=Ext.extend(Ext.SplitButton,{getItemText:function(a){if(a&&this.showText===true){var b="";if(this.prependText){b+=this.prependText}b+=a.text;return b}return undefined},setActiveItem:function(c,a){if(typeof c!="object"){c=this.menu.items.get(c)}if(c){if(!this.rendered){this.text=this.getItemText(c);this.iconCls=c.iconCls}else{var b=this.getItemText(c);if(b){this.setText(b)}this.setIconClass(c.iconCls)}this.activeItem=c;if(!c.checked){c.setChecked(true,true)}if(this.forceIcon){this.setIconClass(this.forceIcon)}if(!a){this.fireEvent("change",this,c)}}},getActiveItem:function(){return this.activeItem},initComponent:function(){this.addEvents("change");if(this.changeHandler){this.on("change",this.changeHandler,this.scope||this);delete this.changeHandler}this.itemCount=this.items.length;this.menu={cls:"x-cycle-menu",items:[]};var d;for(var b=0,a=this.itemCount;b<a;b++){var c=this.items[b];c.group=c.group||this.id;c.itemIndex=b;c.checkHandler=this.checkHandler;c.scope=this;c.checked=c.checked||false;this.menu.items.push(c);if(c.checked){d=c}}this.setActiveItem(d,true);Ext.CycleButton.superclass.initComponent.call(this);this.on("click",this.toggleSelected,this)},checkHandler:function(a,b){if(b){this.setActiveItem(a)}},toggleSelected:function(){this.menu.render();var c,a;for(var b=1;b<this.itemCount;b++){c=(this.activeItem.itemIndex+b)%this.itemCount;a=this.menu.items.itemAt(c);if(!a.disabled){a.setChecked(true);break}}}});Ext.reg("cycle",Ext.CycleButton);