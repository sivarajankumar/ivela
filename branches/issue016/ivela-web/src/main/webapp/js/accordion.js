if (typeof Effect == "undefined") {
    throw ("accordion.js requires including script.aculo.us' effects.js library!")
}
var accordion = Class.create();
accordion.prototype = {
    showAccordion : null,
    currentAccordion : null,
    duration : null,
    effects : [],
    animating : false,
    initialize : function(b, c) {
        if (!$(b)) {
            throw (b + " doesn't exist!");
            return false
        }
        this.options = Object.extend( {
            resizeSpeed : 8,
            classNames : {
                toggle : "accordion_toggle",
                toggleActive : "accordion_toggle_active",
                content : "accordion_content"
            },
            defaultSize : {
                height : null,
                width : null
            },
            direction : "vertical",
            onEvent : "click"
        }, c || {});
        this.duration = ((11 - this.options.resizeSpeed) * 0.15);
        var a;
        if (Prototype.Browser.IE) {
            var r = document, h = Selector.handlers, c = true;
            var n = new Array();
            var elementCount;
            if ((typeof (window.external.AddToFavoritesBar) != "undefined")) {
                // IE 8          
                elementCount = document.all.length;                                 
                if (!elementCount) elementCount = 1;    
                var z = 0;
                for (var i = 0; i < elementCount; i++) {
                    var element = document.all(i);
                    if (element.id == b) {
                        n[z] = element;                  
                        z = z+ 1;
                    }
                }
            } else {
                // IE <= 7               
                elementCount = document.all(b).length;                                 
                if (!elementCount) elementCount = 1;                    
                for (var i = 0; i < elementCount; i++) {
                    n[i] = document.all(b,i);
                }
            }
            c = "descendant";
            a = h.className(n, r, this.options.classNames.toggle, c);

        } else {
            a = $$('#' + b + ' .' + this.options.classNames.toggle);
        }
        a.each( function(d) {
            Event.observe(d, this.options.onEvent, this.activate.bind(this, d),
                    false);
            if (this.options.onEvent == "click") {
                d.onclick = function() {
                    return false
                }
            }
            if (this.options.direction == "horizontal") {
                var e = $H( {
                    width : "0px"
                })
            } else {
                var e = $H( {
                    height : "0px"
                })
            }
            e.merge( {
                display : "none"
            });
            this.currentAccordion = $(d.next(0)).setStyle(e)
        }.bind(this))
    },
    activate : function(a) {
        if (this.animating) {
            return false
        }
        this.effects = [];
        this.currentAccordion = $(a.next(0));
        this.currentAccordion.setStyle( {
            display : "block"
        });
        this.currentAccordion.previous(0).addClassName(
                this.options.classNames.toggleActive);
        if (this.options.direction == "horizontal") {
            this.scaling = $H( {
                scaleX : true,
                scaleY : false
            })
        } else {
            this.scaling = $H( {
                scaleX : false,
                scaleY : true
            })
        }
        if (this.currentAccordion == this.showAccordion) {
            this.deactivate()
        } else {
            this._handleAccordion();
            return this.currentAccordion
        }
    },
    deactivate : function() {
        var a = $H( {
            duration : this.duration,
            scaleContent : false,
            transition : Effect.Transitions.sinoidal,
            queue : {
                position : "end",
                scope : "accordionAnimation"
            },
            scaleMode : {
                originalHeight : this.options.defaultSize.height ? this.options.defaultSize.height
                        : this.currentAccordion.scrollHeight,
                originalWidth : this.options.defaultSize.width ? this.options.defaultSize.width
                        : this.currentAccordion.scrollWidth
            },
            afterFinish : function() {
                this.showAccordion.setStyle( {
                    height : "auto",
                    display : "none"
                });
                this.showAccordion = null;
                this.animating = false
            }.bind(this)
        });
        a.merge(this.scaling);
        this.showAccordion.previous(0).removeClassName(
                this.options.classNames.toggleActive);
        new Effect.Scale(this.showAccordion, 0, a)
    },
    _handleAccordion : function() {
        var a = $H( {
            sync : true,
            scaleFrom : 0,
            scaleContent : false,
            transition : Effect.Transitions.sinoidal,
            scaleMode : {
                originalHeight : this.options.defaultSize.height ? this.options.defaultSize.height
                        : this.currentAccordion.scrollHeight,
                originalWidth : this.options.defaultSize.width ? this.options.defaultSize.width
                        : this.currentAccordion.scrollWidth
            }
        });
        a.merge(this.scaling);
        this.effects.push(new Effect.Scale(this.currentAccordion, 100, a));
        if (this.showAccordion) {
            this.showAccordion.previous(0).removeClassName(
                    this.options.classNames.toggleActive);
            a = $H( {
                sync : true,
                scaleContent : false,
                transition : Effect.Transitions.sinoidal
            });
            a.merge(this.scaling);
            this.effects.push(new Effect.Scale(this.showAccordion, 0, a))
        }
        new Effect.Parallel(this.effects, {
            duration : this.duration,
            queue : {
                position : "end",
                scope : "accordionAnimation"
            },
            beforeStart : function() {
                this.animating = true
            }.bind(this),
            afterFinish : function() {
                if (this.showAccordion) {
                    this.showAccordion.setStyle( {
                        display : "none"
                    })
                }
                $(this.currentAccordion).setStyle( {
                    height : "auto"
                });
                this.showAccordion = this.currentAccordion;
                this.animating = false
            }.bind(this)
        })
    }
};