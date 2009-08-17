/*
#############################################################################################
# Copyright(c) 2009 by IBM Brasil Ltda and others                                           #
# This file is part of ivela project, an open-source                                        #
# Program URL   : http://code.google.com/p/ivela/                                           #  
#                                                                                           #
# This program is free software; you can redistribute it and/or modify it under the terms   #
# of the GNU General Public License as published by the Free Software Foundation; either    #
# version 3 of the License, or (at your option) any later version.                          #
#                                                                                           #
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; #
# without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. #
# See the GNU General Public License for more details.                                      #  
#                                                                                           #
#############################################################################################
# File: prototype.js                                                                        #
# Document: Prototype Java Script                                                           # 
# Date        - Author(Company)                   - Issue# - Summary                        #
# XXXXXXXXXXX - Unknown                           - XXXXXX - Initial Version                #
# 01-JUL-2009 - Fabio Fantato(Instituto Eldorado) - 000010 - Score page does not working    #
#############################################################################################
*/


var Prototype = {
	Version : "1.5.1",
	Browser : {
		IE : !!(window.attachEvent && !window.opera),
		Opera : !!window.opera,
		WebKit : navigator.userAgent.indexOf("AppleWebKit/") > -1,
		Gecko : navigator.userAgent.indexOf("Gecko") > -1
				&& navigator.userAgent.indexOf("KHTML") == -1
	},
	BrowserFeatures : {
		XPath : !!document.evaluate,
		ElementExtensions : !!window.HTMLElement,
		SpecificElementExtensions : (document.createElement("div").__proto__ !== document
				.createElement("form").__proto__)
	},
	ScriptFragment : "<script[^>]*>([\u0001-\uFFFF]*?)<\/script>",
	JSONFilter : /^\/\*-secure-\s*(.*)\s*\*\/\s*$/,
	emptyFunction : function() {
	},
	K : function(a) {
		return a
	}
};
var Class = {
	create : function() {
		return function() {
			this.initialize.apply(this, arguments)
		}
	}
};
var Abstract = new Object();
Object.extend = function(a, c) {
	for ( var b in c) {
		a[b] = c[b]
	}
	return a
};
Object.extend(Object, {
	inspect : function(a) {
		try {
			if (a === undefined) {
				return "undefined"
			}
			if (a === null) {
				return "null"
			}
			return a.inspect ? a.inspect() : a.toString()
		} catch (b) {
			if (b instanceof RangeError) {
				return "..."
			}
			throw b
		}
	},
	toJSON : function(a) {
		var c = typeof a;
		switch (c) {
		case "undefined":
		case "function":
		case "unknown":
			return;
		case "boolean":
			return a.toString()
		}
		if (a === null) {
			return "null"
		}
		if (a.toJSON) {
			return a.toJSON()
		}
		if (a.ownerDocument === document) {
			return
		}
		var b = [];
		for ( var e in a) {
			var d = Object.toJSON(a[e]);
			if (d !== undefined) {
				b.push(e.toJSON() + ": " + d)
			}
		}
		return "{" + b.join(", ") + "}"
	},
	keys : function(a) {
		var b = [];
		for ( var c in a) {
			b.push(c)
		}
		return b
	},
	values : function(b) {
		var a = [];
		for ( var c in b) {
			a.push(b[c])
		}
		return a
	},
	clone : function(a) {
		return Object.extend( {}, a)
	}
});
Function.prototype.bind = function() {
	var a = this, c = $A(arguments), b = c.shift();
	return function() {
		return a.apply(b, c.concat($A(arguments)))
	}
};
Function.prototype.bindAsEventListener = function(c) {
	var a = this, b = $A(arguments), c = b.shift();
	return function(d) {
		return a.apply(c, [ d || window.event ].concat(b))
	}
};
Object.extend(Number.prototype, {
	toColorPart : function() {
		return this.toPaddedString(2, 16)
	},
	succ : function() {
		return this + 1
	},
	times : function(a) {
		$R(0, this, true).each(a);
		return this
	},
	toPaddedString : function(c, b) {
		var a = this.toString(b || 10);
		return "0".times(c - a.length) + a
	},
	toJSON : function() {
		return isFinite(this) ? this.toString() : "null"
	}
});
Date.prototype.toJSON = function() {
	return '"' + this.getFullYear() + "-"
			+ (this.getMonth() + 1).toPaddedString(2) + "-"
			+ this.getDate().toPaddedString(2) + "T"
			+ this.getHours().toPaddedString(2) + ":"
			+ this.getMinutes().toPaddedString(2) + ":"
			+ this.getSeconds().toPaddedString(2) + '"'
};
var Try = {
	these : function() {
		var c;
		for ( var b = 0, d = arguments.length; b < d; b++) {
			var a = arguments[b];
			try {
				c = a();
				break
			} catch (f) {
			}
		}
		return c
	}
};
var PeriodicalExecuter = Class.create();
PeriodicalExecuter.prototype = {
	initialize : function(b, a) {
		this.callback = b;
		this.frequency = a;
		this.currentlyExecuting = false;
		this.registerCallback()
	},
	registerCallback : function() {
		this.timer = setInterval(this.onTimerEvent.bind(this),
				this.frequency * 1000)
	},
	stop : function() {
		if (!this.timer) {
			return
		}
		clearInterval(this.timer);
		this.timer = null
	},
	onTimerEvent : function() {
		if (!this.currentlyExecuting) {
			try {
				this.currentlyExecuting = true;
				this.callback(this)
			} finally {
				this.currentlyExecuting = false
			}
		}
	}
};
Object.extend(String, {
	interpret : function(a) {
		return a == null ? "" : String(a)
	},
	specialChar : {
		"\b" : "\\b",
		"\t" : "\\t",
		"\n" : "\\n",
		"\f" : "\\f",
		"\r" : "\\r",
		"\\" : "\\\\"
	}
});
Object
		.extend(
				String.prototype,
				{
					gsub : function(e, c) {
						var a = "", d = this, b;
						c = arguments.callee.prepareReplacement(c);
						while (d.length > 0) {
							if (b = d.match(e)) {
								a += d.slice(0, b.index);
								a += String.interpret(c(b));
								d = d.slice(b.index + b[0].length)
							} else {
								a += d, d = ""
							}
						}
						return a
					},
					sub : function(c, a, b) {
						a = this.gsub.prepareReplacement(a);
						b = b === undefined ? 1 : b;
						return this.gsub(c, function(d) {
							if (--b < 0) {
								return d[0]
							}
							return a(d)
						})
					},
					scan : function(b, a) {
						this.gsub(b, a);
						return this
					},
					truncate : function(b, a) {
						b = b || 30;
						a = a === undefined ? "..." : a;
						return this.length > b ? this.slice(0, b - a.length)
								+ a : this
					},
					strip : function() {
						return this.replace(/^\s+/, "").replace(/\s+$/, "")
					},
					stripTags : function() {
						return this.replace(/<\/?[^>]+>/gi, "")
					},
					stripScripts : function() {
						return this.replace(new RegExp(
								Prototype.ScriptFragment, "img"), "")
					},
					extractScripts : function() {
						var b = new RegExp(Prototype.ScriptFragment, "img");
						var a = new RegExp(Prototype.ScriptFragment, "im");
						return (this.match(b) || []).map(function(c) {
							return (c.match(a) || [ "", "" ])[1]
						})
					},
					evalScripts : function() {
						return this.extractScripts().map(function(script) {
							return eval(script)
						})
					},
					escapeHTML : function() {
						var a = arguments.callee;
						a.text.data = this;
						return a.div.innerHTML
					},
					unescapeHTML : function() {
						var a = document.createElement("div");
						a.innerHTML = this.stripTags();
						return a.childNodes[0] ? (a.childNodes.length > 1 ? $A(
								a.childNodes).inject("", function(b, c) {
							return b + c.nodeValue
						}) : a.childNodes[0].nodeValue) : ""
					},
					toQueryParams : function(b) {
						var a = this.strip().match(/([^?#]*)(#.*)?$/);
						if (!a) {
							return {}
						}
						return a[1].split(b || "&").inject( {}, function(e, f) {
							if ((f = f.split("="))[0]) {
								var c = decodeURIComponent(f.shift());
								var d = f.length > 1 ? f.join("=") : f[0];
								if (d != undefined) {
									d = decodeURIComponent(d)
								}
								if (c in e) {
									if (e[c].constructor != Array) {
										e[c] = [ e[c] ]
									}
									e[c].push(d)
								} else {
									e[c] = d
								}
							}
							return e
						})
					},
					toArray : function() {
						return this.split("")
					},
					succ : function() {
						return this.slice(0, this.length - 1)
								+ String.fromCharCode(this
										.charCodeAt(this.length - 1) + 1)
					},
					times : function(c) {
						var a = "";
						for ( var b = 0; b < c; b++) {
							a += this
						}
						return a
					},
					camelize : function() {
						var d = this.split("-"), a = d.length;
						if (a == 1) {
							return d[0]
						}
						var c = this.charAt(0) == "-" ? d[0].charAt(0)
								.toUpperCase()
								+ d[0].substring(1) : d[0];
						for ( var b = 1; b < a; b++) {
							c += d[b].charAt(0).toUpperCase()
									+ d[b].substring(1)
						}
						return c
					},
					capitalize : function() {
						return this.charAt(0).toUpperCase()
								+ this.substring(1).toLowerCase()
					},
					underscore : function() {
						return this.gsub(/::/, "/").gsub(
								/([A-Z]+)([A-Z][a-z])/, "#{1}_#{2}").gsub(
								/([a-z\d])([A-Z])/, "#{1}_#{2}").gsub(/-/, "_")
								.toLowerCase()
					},
					dasherize : function() {
						return this.gsub(/_/, "-")
					},
					inspect : function(b) {
						var a = this.gsub(/[\x00-\x1f\\]/, function(c) {
							var d = String.specialChar[c[0]];
							return d ? d : "\\u00"
									+ c[0].charCodeAt().toPaddedString(2, 16)
						});
						if (b) {
							return '"' + a.replace(/"/g, '\\"') + '"'
						}
						return "'" + a.replace(/'/g, "\\'") + "'"
					},
					toJSON : function() {
						return this.inspect(true)
					},
					unfilterJSON : function(a) {
						return this.sub(a || Prototype.JSONFilter, "#{1}")
					},
					evalJSON : function(sanitize) {
						var json = this.unfilterJSON();
						try {
							if (!sanitize
									|| (/^("(\\.|[^"\\\n\r])*?"|[,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t])+?$/
											.test(json))) {
								return eval("(" + json + ")")
							}
						} catch (e) {
						}
						throw new SyntaxError("Badly formed JSON string: "
								+ this.inspect())
					},
					include : function(a) {
						return this.indexOf(a) > -1
					},
					startsWith : function(a) {
						return this.indexOf(a) === 0
					},
					endsWith : function(a) {
						var b = this.length - a.length;
						return b >= 0 && this.lastIndexOf(a) === b
					},
					empty : function() {
						return this == ""
					},
					blank : function() {
						return /^\s*$/.test(this)
					}
				});
if (Prototype.Browser.WebKit || Prototype.Browser.IE) {
	Object.extend(String.prototype, {
		escapeHTML : function() {
			return this.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(
					/>/g, "&gt;")
		},
		unescapeHTML : function() {
			return this.replace(/&amp;/g, "&").replace(/&lt;/g, "<").replace(
					/&gt;/g, ">")
		}
	})
}
String.prototype.gsub.prepareReplacement = function(b) {
	if (typeof b == "function") {
		return b
	}
	var a = new Template(b);
	return function(c) {
		return a.evaluate(c)
	}
};
String.prototype.parseQuery = String.prototype.toQueryParams;
Object.extend(String.prototype.escapeHTML, {
	div : document.createElement("div"),
	text : document.createTextNode("")
});
with (String.prototype.escapeHTML) {
	div.appendChild(text)
}
var Template = Class.create();
Template.Pattern = /(^|.|\r|\n)(#\{(.*?)\})/;
Template.prototype = {
	initialize : function(a, b) {
		this.template = a.toString();
		this.pattern = b || Template.Pattern
	},
	evaluate : function(a) {
		return this.template.gsub(this.pattern, function(b) {
			var c = b[1];
			if (c == "\\") {
				return b[2]
			}
			return c + String.interpret(a[b[3]])
		})
	}
};
var $break = {}, $continue = new Error(
		'"throw $continue" is deprecated, use "return" instead');
var Enumerable = {
	each : function(b) {
		var a = 0;
		try {
			this._each(function(d) {
				b(d, a++)
			})
		} catch (c) {
			if (c != $break) {
				throw c
			}
		}
		return this
	},
	eachSlice : function(c, b) {
		var a = -c, d = [], e = this.toArray();
		while ((a += c) < e.length) {
			d.push(e.slice(a, a + c))
		}
		return d.map(b)
	},
	all : function(b) {
		var a = true;
		this.each(function(d, c) {
			a = a && !!(b || Prototype.K)(d, c);
			if (!a) {
				throw $break
			}
		});
		return a
	},
	any : function(b) {
		var a = false;
		this.each(function(d, c) {
			if (a = !!(b || Prototype.K)(d, c)) {
				throw $break
			}
		});
		return a
	},
	collect : function(b) {
		var a = [];
		this.each(function(d, c) {
			a.push((b || Prototype.K)(d, c))
		});
		return a
	},
	detect : function(b) {
		var a;
		this.each(function(d, c) {
			if (b(d, c)) {
				a = d;
				throw $break
			}
		});
		return a
	},
	findAll : function(b) {
		var a = [];
		this.each(function(d, c) {
			if (b(d, c)) {
				a.push(d)
			}
		});
		return a
	},
	grep : function(c, b) {
		var a = [];
		this.each(function(f, e) {
			var d = f.toString();
			if (d.match(c)) {
				a.push((b || Prototype.K)(f, e))
			}
		});
		return a
	},
	include : function(a) {
		var b = false;
		this.each(function(c) {
			if (c == a) {
				b = true;
				throw $break
			}
		});
		return b
	},
	inGroupsOf : function(b, a) {
		a = a === undefined ? null : a;
		return this.eachSlice(b, function(c) {
			while (c.length < b) {
				c.push(a)
			}
			return c
		})
	},
	inject : function(a, b) {
		this.each(function(d, c) {
			a = b(a, d, c)
		});
		return a
	},
	invoke : function(b) {
		var a = $A(arguments).slice(1);
		return this.map(function(c) {
			return c[b].apply(c, a)
		})
	},
	max : function(b) {
		var a;
		this.each(function(d, c) {
			d = (b || Prototype.K)(d, c);
			if (a == undefined || d >= a) {
				a = d
			}
		});
		return a
	},
	min : function(b) {
		var a;
		this.each(function(d, c) {
			d = (b || Prototype.K)(d, c);
			if (a == undefined || d < a) {
				a = d
			}
		});
		return a
	},
	partition : function(c) {
		var b = [], a = [];
		this.each(function(e, d) {
			((c || Prototype.K)(e, d) ? b : a).push(e)
		});
		return [ b, a ]
	},
	pluck : function(b) {
		var a = [];
		this.each(function(d, c) {
			a.push(d[b])
		});
		return a
	},
	reject : function(b) {
		var a = [];
		this.each(function(d, c) {
			if (!b(d, c)) {
				a.push(d)
			}
		});
		return a
	},
	sortBy : function(a) {
		return this.map(function(c, b) {
			return {
				value : c,
				criteria : a(c, b)
			}
		}).sort(function(f, e) {
			var d = f.criteria, c = e.criteria;
			return d < c ? -1 : d > c ? 1 : 0
		}).pluck("value")
	},
	toArray : function() {
		return this.map()
	},
	zip : function() {
		var b = Prototype.K, a = $A(arguments);
		if (typeof a.last() == "function") {
			b = a.pop()
		}
		var c = [ this ].concat(a).map($A);
		return this.map(function(e, d) {
			return b(c.pluck(d))
		})
	},
	size : function() {
		return this.toArray().length
	},
	inspect : function() {
		return "#<Enumerable:" + this.toArray().inspect() + ">"
	}
};
Object.extend(Enumerable, {
	map : Enumerable.collect,
	find : Enumerable.detect,
	select : Enumerable.findAll,
	member : Enumerable.include,
	entries : Enumerable.toArray
});
var $A = Array.from = function(d) {
	if (!d) {
		return []
	}
	if (d.toArray) {
		return d.toArray()
	} else {
		var b = [];
		for ( var a = 0, c = d.length; a < c; a++) {
			b.push(d[a])
		}
		return b
	}
};
if (Prototype.Browser.WebKit) {
	$A = Array.from = function(d) {
		if (!d) {
			return []
		}
		if (!(typeof d == "function" && d == "[object NodeList]") && d.toArray) {
			return d.toArray()
		} else {
			var b = [];
			for ( var a = 0, c = d.length; a < c; a++) {
				b.push(d[a])
			}
			return b
		}
	}
}
Object.extend(Array.prototype, Enumerable);
if (!Array.prototype._reverse) {
	Array.prototype._reverse = Array.prototype.reverse
}
Object.extend(Array.prototype, {
	_each : function(b) {
		for ( var a = 0, c = this.length; a < c; a++) {
			b(this[a])
		}
	},
	clear : function() {
		this.length = 0;
		return this
	},
	first : function() {
		return this[0]
	},
	last : function() {
		return this[this.length - 1]
	},
	compact : function() {
		return this.select(function(a) {
			return a != null
		})
	},
	flatten : function() {
		return this.inject( [], function(b, a) {
			return b.concat(a && a.constructor == Array ? a.flatten() : [ a ])
		})
	},
	without : function() {
		var a = $A(arguments);
		return this.select(function(b) {
			return !a.include(b)
		})
	},
	indexOf : function(a) {
		for ( var b = 0, c = this.length; b < c; b++) {
			if (this[b] == a) {
				return b
			}
		}
		return -1
	},
	reverse : function(a) {
		return (a !== false ? this : this.toArray())._reverse()
	},
	reduce : function() {
		return this.length > 1 ? this : this[0]
	},
	uniq : function(a) {
		return this.inject( [], function(d, c, b) {
			if (0 == b || (a ? d.last() != c : !d.include(c))) {
				d.push(c)
			}
			return d
		})
	},
	clone : function() {
		return [].concat(this)
	},
	size : function() {
		return this.length
	},
	inspect : function() {
		return "[" + this.map(Object.inspect).join(", ") + "]"
	},
	toJSON : function() {
		var a = [];
		this.each(function(b) {
			var c = Object.toJSON(b);
			if (c !== undefined) {
				a.push(c)
			}
		});
		return "[" + a.join(", ") + "]"
	}
});
Array.prototype.toArray = Array.prototype.clone;
function $w(a) {
	a = a.strip();
	return a ? a.split(/\s+/) : []
}
if (Prototype.Browser.Opera) {
	Array.prototype.concat = function() {
		var e = [];
		for ( var b = 0, c = this.length; b < c; b++) {
			e.push(this[b])
		}
		for ( var b = 0, c = arguments.length; b < c; b++) {
			if (arguments[b].constructor == Array) {
				for ( var a = 0, d = arguments[b].length; a < d; a++) {
					e.push(arguments[b][a])
				}
			} else {
				e.push(arguments[b])
			}
		}
		return e
	}
}
var Hash = function(a) {
	if (a instanceof Hash) {
		this.merge(a)
	} else {
		Object.extend(this, a || {})
	}
};
Object.extend(Hash, {
	toQueryString : function(b) {
		var a = [];
		a.add = arguments.callee.addPair;
		this.prototype._each.call(b, function(d) {
			if (!d.key) {
				return
			}
			var c = d.value;
			if (c && typeof c == "object") {
				if (c.constructor == Array) {
					c.each(function(e) {
						a.add(d.key, e)
					})
				}
				return
			}
			a.add(d.key, c)
		});
		return a.join("&")
	},
	toJSON : function(a) {
		var b = [];
		this.prototype._each.call(a, function(d) {
			var c = Object.toJSON(d.value);
			if (c !== undefined) {
				b.push(d.key.toJSON() + ": " + c)
			}
		});
		return "{" + b.join(", ") + "}"
	}
});
Hash.toQueryString.addPair = function(a, c, b) {
	a = encodeURIComponent(a);
	if (c === undefined) {
		this.push(a)
	} else {
		this.push(a + "=" + (c == null ? "" : encodeURIComponent(c)))
	}
};
Object.extend(Hash.prototype, Enumerable);
Object.extend(Hash.prototype, {
	_each : function(b) {
		for ( var a in this) {
			var c = this[a];
			if (c && c == Hash.prototype[a]) {
				continue
			}
			var d = [ a, c ];
			d.key = a;
			d.value = c;
			b(d)
		}
	},
	keys : function() {
		return this.pluck("key")
	},
	values : function() {
		return this.pluck("value")
	},
	merge : function(a) {
		return $H(a).inject(this, function(b, c) {
			b[c.key] = c.value;
			return b
		})
	},
	remove : function() {
		var a;
		for ( var b = 0, c = arguments.length; b < c; b++) {
			var d = this[arguments[b]];
			if (d !== undefined) {
				if (a === undefined) {
					a = d
				} else {
					if (a.constructor != Array) {
						a = [ a ]
					}
					a.push(d)
				}
			}
			delete this[arguments[b]]
		}
		return a
	},
	toQueryString : function() {
		return Hash.toQueryString(this)
	},
	inspect : function() {
		return "#<Hash:{" + this.map(function(a) {
			return a.map(Object.inspect).join(": ")
		}).join(", ") + "}>"
	},
	toJSON : function() {
		return Hash.toJSON(this)
	}
});
function $H(a) {
	if (a instanceof Hash) {
		return a
	}
	return new Hash(a)
}
if (function() {
	var a = 0, c = function(d) {
		this.key = d
	};
	c.prototype.key = "foo";
	for ( var b in new c("bar")) {
		a++
	}
	return a > 1
}()) {
	Hash.prototype._each = function(c) {
		var a = [];
		for ( var b in this) {
			var d = this[b];
			if ((d && d == Hash.prototype[b]) || a.include(b)) {
				continue
			}
			a.push(b);
			var e = [ b, d ];
			e.key = b;
			e.value = d;
			c(e)
		}
	}
}
ObjectRange = Class.create();
Object.extend(ObjectRange.prototype, Enumerable);
Object.extend(ObjectRange.prototype, {
	initialize : function(c, a, b) {
		this.start = c;
		this.end = a;
		this.exclusive = b
	},
	_each : function(a) {
		var b = this.start;
		while (this.include(b)) {
			a(b);
			b = b.succ()
		}
	},
	include : function(a) {
		if (a < this.start) {
			return false
		}
		if (this.exclusive) {
			return a < this.end
		}
		return a <= this.end
	}
});
var $R = function(c, a, b) {
	return new ObjectRange(c, a, b)
};
var Ajax = {
	getTransport : function() {
		return Try.these(function() {
			return new XMLHttpRequest()
		}, function() {
			return new ActiveXObject("Msxml2.XMLHTTP")
		}, function() {
			return new ActiveXObject("Microsoft.XMLHTTP")
		}) || false
	},
	activeRequestCount : 0
};
Ajax.Responders = {
	responders : [],
	_each : function(a) {
		this.responders._each(a)
	},
	register : function(a) {
		if (!this.include(a)) {
			this.responders.push(a)
		}
	},
	unregister : function(a) {
		this.responders = this.responders.without(a)
	},
	dispatch : function(d, b, c, a) {
		this.each(function(f) {
			if (typeof f[d] == "function") {
				try {
					f[d].apply(f, [ b, c, a ])
				} catch (g) {
				}
			}
		})
	}
};
Object.extend(Ajax.Responders, Enumerable);
Ajax.Responders.register( {
	onCreate : function() {
		Ajax.activeRequestCount++
	},
	onComplete : function() {
		Ajax.activeRequestCount--
	}
});
Ajax.Base = function() {
};
Ajax.Base.prototype = {
	setOptions : function(a) {
		this.options = {
			method : "post",
			asynchronous : true,
			contentType : "application/x-www-form-urlencoded",
			encoding : "UTF-8",
			parameters : ""
		};
		Object.extend(this.options, a || {});
		this.options.method = this.options.method.toLowerCase();
		if (typeof this.options.parameters == "string") {
			this.options.parameters = this.options.parameters.toQueryParams()
		}
	}
};
Ajax.Request = Class.create();
Ajax.Request.Events = [ "Uninitialized", "Loading", "Loaded", "Interactive",
		"Complete" ];
Ajax.Request.prototype = Object
		.extend(
				new Ajax.Base(),
				{
					_complete : false,
					initialize : function(b, a) {
						this.transport = Ajax.getTransport();
						this.setOptions(a);
						this.request(b)
					},
					request : function(a) {
						this.url = a;
						this.method = this.options.method;
						var c = Object.clone(this.options.parameters);
						if (! [ "get", "post" ].include(this.method)) {
							c._method = this.method;
							this.method = "post"
						}
						this.parameters = c;
						if (c = Hash.toQueryString(c)) {
							if (this.method == "get") {
								this.url += (this.url.include("?") ? "&" : "?")
										+ c
							} else {
								if (/Konqueror|Safari|KHTML/
										.test(navigator.userAgent)) {
									c += "&_="
								}
							}
						}
						try {
							if (this.options.onCreate) {
								this.options.onCreate(this.transport)
							}
							Ajax.Responders.dispatch("onCreate", this,
									this.transport);
							this.transport.open(this.method.toUpperCase(),
									this.url, this.options.asynchronous);
							if (this.options.asynchronous) {
								setTimeout(function() {
									this.respondToReadyState(1)
								}.bind(this), 10)
							}
							this.transport.onreadystatechange = this.onStateChange
									.bind(this);
							this.setRequestHeaders();
							this.body = this.method == "post" ? (this.options.postBody || c)
									: null;
							this.transport.send(this.body);
							if (!this.options.asynchronous
									&& this.transport.overrideMimeType) {
								this.onStateChange()
							}
						} catch (b) {
							this.dispatchException(b)
						}
					},
					onStateChange : function() {
						var a = this.transport.readyState;
						if (a > 1 && !((a == 4) && this._complete)) {
							this.respondToReadyState(this.transport.readyState)
						}
					},
					setRequestHeaders : function() {
						var e = {
							"X-Requested-With" : "XMLHttpRequest",
							"X-Prototype-Version" : Prototype.Version,
							Accept : "text/javascript, text/html, application/xml, text/xml, */*"
						};
						if (this.method == "post") {
							e["Content-type"] = this.options.contentType
									+ (this.options.encoding ? "; charset="
											+ this.options.encoding : "");
							if (this.transport.overrideMimeType
									&& (navigator.userAgent
											.match(/Gecko\/(\d{4})/) || [ 0,
											2005 ])[1] < 2005) {
								e.Connection = "close"
							}
						}
						if (typeof this.options.requestHeaders == "object") {
							var c = this.options.requestHeaders;
							if (typeof c.push == "function") {
								for ( var b = 0, d = c.length; b < d; b += 2) {
									e[c[b]] = c[b + 1]
								}
							} else {
								$H(c).each(function(f) {
									e[f.key] = f.value
								})
							}
						}
						for ( var a in e) {
							this.transport.setRequestHeader(a, e[a])
						}
					},
					success : function() {
						return !this.transport.status
								|| (this.transport.status >= 200 && this.transport.status < 300)
					},
					respondToReadyState : function(a) {
						var c = Ajax.Request.Events[a];
						var g = this.transport, b = this.evalJSON();
						if (c == "Complete") {
							try {
								this._complete = true;
								(this.options["on" + this.transport.status]
										|| this.options["on"
												+ (this.success() ? "Success"
														: "Failure")] || Prototype.emptyFunction)
										(g, b)
							} catch (d) {
								this.dispatchException(d)
							}
							var f = this.getHeader("Content-type");
							if (f
									&& f
											.strip()
											.match(
													/^(text|application)\/(x-)?(java|ecma)script(;.*)?$/i)) {
								this.evalResponse()
							}
						}
						try {
							(this.options["on" + c] || Prototype.emptyFunction)
									(g, b);
							Ajax.Responders.dispatch("on" + c, this, g, b)
						} catch (d) {
							this.dispatchException(d)
						}
						if (c == "Complete") {
							this.transport.onreadystatechange = Prototype.emptyFunction
						}
					},
					getHeader : function(a) {
						try {
							return this.transport.getResponseHeader(a)
						} catch (b) {
							return null
						}
					},
					evalJSON : function() {
						try {
							var a = this.getHeader("X-JSON");
							return a ? a.evalJSON() : null
						} catch (b) {
							return null
						}
					},
					evalResponse : function() {
						try {
							return eval((this.transport.responseText || "")
									.unfilterJSON())
						} catch (e) {
							this.dispatchException(e)
						}
					},
					dispatchException : function(a) {
						(this.options.onException || Prototype.emptyFunction)(
								this, a);
						Ajax.Responders.dispatch("onException", this, a)
					}
				});
Ajax.Updater = Class.create();
Object.extend(Object.extend(Ajax.Updater.prototype, Ajax.Request.prototype), {
	initialize : function(a, c, b) {
		this.container = {
			success : (a.success || a),
			failure : (a.failure || (a.success ? null : a))
		};
		this.transport = Ajax.getTransport();
		this.setOptions(b);
		var d = this.options.onComplete || Prototype.emptyFunction;
		this.options.onComplete = (function(f, e) {
			this.updateContent();
			d(f, e)
		}).bind(this);
		this.request(c)
	},
	updateContent : function() {
		var b = this.container[this.success() ? "success" : "failure"];
		var a = this.transport.responseText;
		if (!this.options.evalScripts) {
			a = a.stripScripts()
		}
		if (b = $(b)) {
			if (this.options.insertion) {
				new this.options.insertion(b, a)
			} else {
				b.update(a)
			}
		}
		if (this.success()) {
			if (this.onComplete) {
				setTimeout(this.onComplete.bind(this), 10)
			}
		}
	}
});
Ajax.PeriodicalUpdater = Class.create();
Ajax.PeriodicalUpdater.prototype = Object.extend(new Ajax.Base(), {
	initialize : function(a, c, b) {
		this.setOptions(b);
		this.onComplete = this.options.onComplete;
		this.frequency = (this.options.frequency || 2);
		this.decay = (this.options.decay || 1);
		this.updater = {};
		this.container = a;
		this.url = c;
		this.start()
	},
	start : function() {
		this.options.onComplete = this.updateComplete.bind(this);
		this.onTimerEvent()
	},
	stop : function() {
		this.updater.options.onComplete = undefined;
		clearTimeout(this.timer);
		(this.onComplete || Prototype.emptyFunction).apply(this, arguments)
	},
	updateComplete : function(a) {
		if (this.options.decay) {
			this.decay = (a.responseText == this.lastText ? this.decay
					* this.options.decay : 1);
			this.lastText = a.responseText
		}
		this.timer = setTimeout(this.onTimerEvent.bind(this), this.decay
				* this.frequency * 1000)
	},
	onTimerEvent : function() {
		this.updater = new Ajax.Updater(this.container, this.url, this.options)
	}
});
function $(b) {
	if (arguments.length > 1) {
		for ( var a = 0, d = [], c = arguments.length; a < c; a++) {
			d.push($(arguments[a]))
		}
		return d
	}
	if (typeof b == "string") {
		b = document.getElementById(b)
	}
	return Element.extend(b)
}
if (Prototype.BrowserFeatures.XPath) {
	document._getElementsByXPath = function(f, a) {
		var c = [];
		var e = document.evaluate(f, $(a) || document, null,
				XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
		for ( var b = 0, d = e.snapshotLength; b < d; b++) {
			c.push(e.snapshotItem(b))
		}
		return c
	};
	document.getElementsByClassName = function(b, a) {
		var c = ".//*[contains(concat(' ', @class, ' '), ' " + b + " ')]";
		return document._getElementsByXPath(c, a)
	}
} else {
	document.getElementsByClassName = function(d, a) {
		var c = ($(a) || document.body).getElementsByTagName("*");
		var f = [], g;
		for ( var b = 0, e = c.length; b < e; b++) {
			g = c[b];
			if (Element.hasClassName(g, d)) {
				f.push(Element.extend(g))
			}
		}
		return f
	}
}
if (!window.Element) {
	var Element = {}
}
Element.extend = function(e) {
	var f = Prototype.BrowserFeatures;
	if (!e || !e.tagName || e.nodeType == 3 || e._extended
			|| f.SpecificElementExtensions || e == window) {
		return e
	}
	var b = {}, d = e.tagName, a = Element.extend.cache, c = Element.Methods.ByTag;
	if (!f.ElementExtensions) {
		Object.extend(b, Element.Methods), Object.extend(b,
				Element.Methods.Simulated)
	}
	if (c[d]) {
		Object.extend(b, c[d])
	}
	for ( var h in b) {
		var g = b[h];
		if (typeof g == "function" && !(h in e)) {
			e[h] = a.findOrStore(g)
		}
	}
	e._extended = Prototype.emptyFunction;
	return e
};
Element.extend.cache = {
	findOrStore : function(a) {
		return this[a] = this[a] || function() {
			return a.apply(null, [ this ].concat($A(arguments)))
		}
	}
};
Element.Methods = {
	visible : function(a) {
		return $(a).style.display != "none"
	},
	toggle : function(a) {
		a = $(a);
		Element[Element.visible(a) ? "hide" : "show"](a);
		return a
	},
	hide : function(a) {
		$(a).style.display = "none";
		return a
	},
	show : function(a) {
		$(a).style.display = "";
		return a
	},
	remove : function(a) {
		a = $(a);
		a.parentNode.removeChild(a);
		return a
	},
	update : function(b, a) {
		a = typeof a == "undefined" ? "" : a.toString();
		$(b).innerHTML = a.stripScripts();
		setTimeout(function() {
			a.evalScripts()
		}, 10);
		return b
	},
	replace : function(c, b) {
		c = $(c);
		b = typeof b == "undefined" ? "" : b.toString();
		if (c.outerHTML) {
			c.outerHTML = b.stripScripts()
		} else {
			var a = c.ownerDocument.createRange();
			a.selectNodeContents(c);
			c.parentNode.replaceChild(a.createContextualFragment(b
					.stripScripts()), c)
		}
		setTimeout(function() {
			b.evalScripts()
		}, 10);
		return c
	},
	inspect : function(b) {
		b = $(b);
		var a = "<" + b.tagName.toLowerCase();
		$H( {
			id : "id",
			className : "class"
		}).each(function(f) {
			var e = f.first(), c = f.last();
			var d = (b[e] || "").toString();
			if (d) {
				a += " " + c + "=" + d.inspect(true)
			}
		});
		return a + ">"
	},
	recursivelyCollect : function(a, c) {
		a = $(a);
		var b = [];
		while (a = a[c]) {
			if (a.nodeType == 1) {
				b.push(Element.extend(a))
			}
		}
		return b
	},
	ancestors : function(a) {
		return $(a).recursivelyCollect("parentNode")
	},
	descendants : function(a) {
		return $A($(a).getElementsByTagName("*")).each(Element.extend)
	},
	firstDescendant : function(a) {
		a = $(a).firstChild;
		while (a && a.nodeType != 1) {
			a = a.nextSibling
		}
		return $(a)
	},
	immediateDescendants : function(a) {
		if (!(a = $(a).firstChild)) {
			return []
		}
		while (a && a.nodeType != 1) {
			a = a.nextSibling
		}
		if (a) {
			return [ a ].concat($(a).nextSiblings())
		}
		return []
	},
	previousSiblings : function(a) {
		return $(a).recursivelyCollect("previousSibling")
	},
	nextSiblings : function(a) {
		return $(a).recursivelyCollect("nextSibling")
	},
	siblings : function(a) {
		a = $(a);
		return a.previousSiblings().reverse().concat(a.nextSiblings())
	},
	match : function(b, a) {
		if (typeof a == "string") {
			a = new Selector(a)
		}
		return a.match($(b))
	},
	up : function(b, d, a) {
		b = $(b);
		if (arguments.length == 1) {
			return $(b.parentNode)
		}
		var c = b.ancestors();
		return d ? Selector.findElement(c, d, a) : c[a || 0]
	},
	down : function(b, c, a) {
		b = $(b);
		if (arguments.length == 1) {
			return b.firstDescendant()
		}
		var d = b.descendants();
		return c ? Selector.findElement(d, c, a) : d[a || 0]
	},
	previous : function(b, d, a) {
		b = $(b);
		if (arguments.length == 1) {
			return $(Selector.handlers.previousElementSibling(b))
		}
		var c = b.previousSiblings();
		return d ? Selector.findElement(c, d, a) : c[a || 0]
	},
	next : function(c, d, b) {
		c = $(c);
		if (arguments.length == 1) {
			return $(Selector.handlers.nextElementSibling(c))
		}
		var a = c.nextSiblings();
		return d ? Selector.findElement(a, d, b) : a[b || 0]
	},
	getElementsBySelector : function() {
		var a = $A(arguments), b = $(a.shift());
		return Selector.findChildElements(b, a)
	},
	getElementsByClassName : function(a, b) {
		return document.getElementsByClassName(b, a)
	},
	readAttribute : function(c, a) {
		c = $(c);
		if (Prototype.Browser.IE) {
			if (!c.attributes) {
				return null
			}
			var b = Element._attributeTranslations;
			if (b.values[a]) {
				return b.values[a](c, a)
			}
			if (b.names[a]) {
				a = b.names[a]
			}
			var d = c.attributes[a];
			return d ? d.nodeValue : null
		}
		return c.getAttribute(a)
	},
	getHeight : function(a) {
		return $(a).getDimensions().height
	},
	getWidth : function(a) {
		return $(a).getDimensions().width
	},
	classNames : function(a) {
		return new Element.ClassNames(a)
	},
	hasClassName : function(a, b) {
		if (!(a = $(a))) {
			return
		}
		var c = a.className;
		if (c.length == 0) {
			return false
		}
		if (c == b || c.match(new RegExp("(^|\\s)" + b + "(\\s|$)"))) {
			return true
		}
		return false
	},
	addClassName : function(a, b) {
		if (!(a = $(a))) {
			return
		}
		Element.classNames(a).add(b);
		return a
	},
	removeClassName : function(a, b) {
		if (!(a = $(a))) {
			return
		}
		Element.classNames(a).remove(b);
		return a
	},
	toggleClassName : function(a, b) {
		if (!(a = $(a))) {
			return
		}
		Element.classNames(a)[a.hasClassName(b) ? "remove" : "add"](b);
		return a
	},
	observe : function() {
		Event.observe.apply(Event, arguments);
		return $A(arguments).first()
	},
	stopObserving : function() {
		Event.stopObserving.apply(Event, arguments);
		return $A(arguments).first()
	},
	cleanWhitespace : function(b) {
		b = $(b);
		var c = b.firstChild;
		while (c) {
			var a = c.nextSibling;
			if (c.nodeType == 3 && !/\S/.test(c.nodeValue)) {
				b.removeChild(c)
			}
			c = a
		}
		return b
	},
	empty : function(a) {
		return $(a).innerHTML.blank()
	},
	descendantOf : function(b, a) {
		b = $(b), a = $(a);
		while (b = b.parentNode) {
			if (b == a) {
				return true
			}
		}
		return false
	},
	scrollTo : function(a) {
		a = $(a);
		var b = Position.cumulativeOffset(a);
		window.scrollTo(b[0], b[1]);
		return a
	},
	getStyle : function(b, c) {
		b = $(b);
		c = c == "float" ? "cssFloat" : c.camelize();
		var d = b.style[c];
		if (!d) {
			var a = document.defaultView.getComputedStyle(b, null);
			d = a ? a[c] : null
		}
		if (c == "opacity") {
			return d ? parseFloat(d) : 1
		}
		return d == "auto" ? null : d
	},
	getOpacity : function(a) {
		return $(a).getStyle("opacity")
	},
	setStyle : function(a, c, b) {
		a = $(a);
		var e = a.style;
		for ( var d in c) {
			if (d == "opacity") {
				a.setOpacity(c[d])
			} else {
				e[(d == "float" || d == "cssFloat") ? (e.styleFloat === undefined ? "cssFloat"
						: "styleFloat")
						: (b ? d : d.camelize())] = c[d]
			}
		}
		return a
	},
	setOpacity : function(a, b) {
		a = $(a);
		a.style.opacity = (b == 1 || b === "") ? "" : (b < 0.00001) ? 0 : b;
		return a
	},
	getDimensions : function(c) {
		c = $(c);
		var g = $(c).getStyle("display");
		if (g != "none" && g != null) {
			return {
				width : c.offsetWidth,
				height : c.offsetHeight
			}
		}
		var b = c.style;
		var f = b.visibility;
		var d = b.position;
		var a = b.display;
		b.visibility = "hidden";
		b.position = "absolute";
		b.display = "block";
		var h = c.clientWidth;
		var e = c.clientHeight;
		b.display = a;
		b.position = d;
		b.visibility = f;
		return {
			width : h,
			height : e
		}
	},
	makePositioned : function(a) {
		a = $(a);
		var b = Element.getStyle(a, "position");
		if (b == "static" || !b) {
			a._madePositioned = true;
			a.style.position = "relative";
			if (window.opera) {
				a.style.top = 0;
				a.style.left = 0
			}
		}
		return a
	},
	undoPositioned : function(a) {
		a = $(a);
		if (a._madePositioned) {
			a._madePositioned = undefined;
			a.style.position = a.style.top = a.style.left = a.style.bottom = a.style.right = ""
		}
		return a
	},
	makeClipping : function(a) {
		a = $(a);
		if (a._overflow) {
			return a
		}
		a._overflow = a.style.overflow || "auto";
		if ((Element.getStyle(a, "overflow") || "visible") != "hidden") {
			a.style.overflow = "hidden"
		}
		return a
	},
	undoClipping : function(a) {
		a = $(a);
		if (!a._overflow) {
			return a
		}
		a.style.overflow = a._overflow == "auto" ? "" : a._overflow;
		a._overflow = null;
		return a
	}
};
Object.extend(Element.Methods, {
	childOf : Element.Methods.descendantOf,
	childElements : Element.Methods.immediateDescendants
});
if (Prototype.Browser.Opera) {
	Element.Methods._getStyle = Element.Methods.getStyle;
	Element.Methods.getStyle = function(a, b) {
		switch (b) {
		case "left":
		case "top":
		case "right":
		case "bottom":
			if (Element._getStyle(a, "position") == "static") {
				return null
			}
		default:
			return Element._getStyle(a, b)
		}
	}
} else {
	if (Prototype.Browser.IE) {
		Element.Methods.getStyle = function(a, b) {
			a = $(a);
			b = (b == "float" || b == "cssFloat") ? "styleFloat" : b.camelize();
			var c = a.style[b];
			if (!c && a.currentStyle) {
				c = a.currentStyle[b]
			}
			if (b == "opacity") {
				if (c = (a.getStyle("filter") || "")
						.match(/alpha\(opacity=(.*)\)/)) {
					if (c[1]) {
						return parseFloat(c[1]) / 100
					}
				}
				return 1
			}
			if (c == "auto") {
				if ((b == "width" || b == "height")
						&& (a.getStyle("display") != "none")) {
					return a["offset" + b.capitalize()] + "px"
				}
				return null
			}
			return c
		};
		Element.Methods.setOpacity = function(a, d) {
			a = $(a);
			var c = a.getStyle("filter"), b = a.style;
			if (d == 1 || d === "") {
				b.filter = c.replace(/alpha\([^\)]*\)/gi, "");
				return a
			} else {
				if (d < 0.00001) {
					d = 0
				}
			}
			b.filter = c.replace(/alpha\([^\)]*\)/gi, "") + "alpha(opacity="
					+ (d * 100) + ")";
			return a
		};
		Element.Methods.update = function(c, b) {
			c = $(c);
			b = typeof b == "undefined" ? "" : b.toString();
			var a = c.tagName.toUpperCase();
			if ( [ "THEAD", "TBODY", "TR", "TD" ].include(a)) {
				var d = document.createElement("div");
				switch (a) {
				case "THEAD":
				case "TBODY":
					d.innerHTML = "<table><tbody>" + b.stripScripts()
							+ "</tbody></table>";
					depth = 2;
					break;
				case "TR":
					d.innerHTML = "<table><tbody><tr>" + b.stripScripts()
							+ "</tr></tbody></table>";
					depth = 3;
					break;
				case "TD":
					d.innerHTML = "<table><tbody><tr><td>" + b.stripScripts()
							+ "</td></tr></tbody></table>";
					depth = 4
				}
				$A(c.childNodes).each(function(e) {
					c.removeChild(e)
				});
				depth.times(function() {
					d = d.firstChild
				});
				$A(d.childNodes).each(function(e) {
					c.appendChild(e)
				})
			} else {
				c.innerHTML = b.stripScripts()
			}
			setTimeout(function() {
				b.evalScripts()
			}, 10);
			return c
		}
	} else {
		if (Prototype.Browser.Gecko) {
			Element.Methods.setOpacity = function(a, b) {
				a = $(a);
				a.style.opacity = (b == 1) ? 0.999999 : (b === "") ? ""
						: (b < 0.00001) ? 0 : b;
				return a
			}
		}
	}
}
Element._attributeTranslations = {
	names : {
		colspan : "colSpan",
		rowspan : "rowSpan",
		valign : "vAlign",
		datetime : "dateTime",
		accesskey : "accessKey",
		tabindex : "tabIndex",
		enctype : "encType",
		maxlength : "maxLength",
		readonly : "readOnly",
		longdesc : "longDesc"
	},
	values : {
		_getAttr : function(a, b) {
			return a.getAttribute(b, 2)
		},
		_flag : function(a, b) {
			return $(a).hasAttribute(b) ? b : null
		},
		style : function(a) {
			return a.style.cssText.toLowerCase()
		},
		title : function(a) {
			var b = a.getAttributeNode("title");
			return b.specified ? b.nodeValue : null
		}
	}
};
(function() {
	Object.extend(this, {
		href : this._getAttr,
		src : this._getAttr,
		type : this._getAttr,
		disabled : this._flag,
		checked : this._flag,
		readonly : this._flag,
		multiple : this._flag
	})
}).call(Element._attributeTranslations.values);
Element.Methods.Simulated = {
	hasAttribute : function(b, d) {
		var a = Element._attributeTranslations, c;
		d = a.names[d] || d;
		c = $(b).getAttributeNode(d);
		return c && c.specified
	}
};
Element.Methods.ByTag = {};
Object.extend(Element, Element.Methods);
if (!Prototype.BrowserFeatures.ElementExtensions
		&& document.createElement("div").__proto__) {
	window.HTMLElement = {};
	window.HTMLElement.prototype = document.createElement("div").__proto__;
	Prototype.BrowserFeatures.ElementExtensions = true
}
Element.hasAttribute = function(a, b) {
	if (a.hasAttribute) {
		return a.hasAttribute(b)
	}
	return Element.Methods.Simulated.hasAttribute(a, b)
};
Element.addMethods = function(c) {
	var h = Prototype.BrowserFeatures, d = Element.Methods.ByTag;
	if (!c) {
		Object.extend(Form, Form.Methods);
		Object.extend(Form.Element, Form.Element.Methods);
		Object.extend(Element.Methods.ByTag, {
			FORM : Object.clone(Form.Methods),
			INPUT : Object.clone(Form.Element.Methods),
			SELECT : Object.clone(Form.Element.Methods),
			TEXTAREA : Object.clone(Form.Element.Methods)
		})
	}
	if (arguments.length == 2) {
		var b = c;
		c = arguments[1]
	}
	if (!b) {
		Object.extend(Element.Methods, c || {})
	} else {
		if (b.constructor == Array) {
			b.each(g)
		} else {
			g(b)
		}
	}
	function g(j) {
		j = j.toUpperCase();
		if (!Element.Methods.ByTag[j]) {
			Element.Methods.ByTag[j] = {}
		}
		Object.extend(Element.Methods.ByTag[j], c)
	}
	function a(m, k, j) {
		j = j || false;
		var l = Element.extend.cache;
		for ( var o in m) {
			var n = m[o];
			if (!j || !(o in k)) {
				k[o] = l.findOrStore(n)
			}
		}
	}
	function e(l) {
		var j;
		var k = {
			OPTGROUP : "OptGroup",
			TEXTAREA : "TextArea",
			P : "Paragraph",
			FIELDSET : "FieldSet",
			UL : "UList",
			OL : "OList",
			DL : "DList",
			DIR : "Directory",
			H1 : "Heading",
			H2 : "Heading",
			H3 : "Heading",
			H4 : "Heading",
			H5 : "Heading",
			H6 : "Heading",
			Q : "Quote",
			INS : "Mod",
			DEL : "Mod",
			A : "Anchor",
			IMG : "Image",
			CAPTION : "TableCaption",
			COL : "TableCol",
			COLGROUP : "TableCol",
			THEAD : "TableSection",
			TFOOT : "TableSection",
			TBODY : "TableSection",
			TR : "TableRow",
			TH : "TableCell",
			TD : "TableCell",
			FRAMESET : "FrameSet",
			IFRAME : "IFrame"
		};
		if (k[l]) {
			j = "HTML" + k[l] + "Element"
		}
		if (window[j]) {
			return window[j]
		}
		j = "HTML" + l + "Element";
		if (window[j]) {
			return window[j]
		}
		j = "HTML" + l.capitalize() + "Element";
		if (window[j]) {
			return window[j]
		}
		window[j] = {};
		window[j].prototype = document.createElement(l).__proto__;
		return window[j]
	}
	if (h.ElementExtensions) {
		a(Element.Methods, HTMLElement.prototype);
		a(Element.Methods.Simulated, HTMLElement.prototype, true)
	}
	if (h.SpecificElementExtensions) {
		for ( var i in Element.Methods.ByTag) {
			var f = e(i);
			if (typeof f == "undefined") {
				continue
			}
			a(d[i], f.prototype)
		}
	}
	Object.extend(Element, Element.Methods);
	delete Element.ByTag
};
var Toggle = {
	display : Element.toggle
};
Abstract.Insertion = function(a) {
	this.adjacency = a
};
Abstract.Insertion.prototype = {
	initialize : function(b, c) {
		this.element = $(b);
		this.content = c.stripScripts();
		if (this.adjacency && this.element.insertAdjacentHTML) {
			try {
				this.element.insertAdjacentHTML(this.adjacency, this.content)
			} catch (d) {
				var a = this.element.tagName.toUpperCase();
				if ( [ "TBODY", "TR" ].include(a)) {
					this.insertContent(this.contentFromAnonymousTable())
				} else {
					throw d
				}
			}
		} else {
			this.range = this.element.ownerDocument.createRange();
			if (this.initializeRange) {
				this.initializeRange()
			}
			this.insertContent( [ this.range
					.createContextualFragment(this.content) ])
		}
		setTimeout(function() {
			c.evalScripts()
		}, 10)
	},
	contentFromAnonymousTable : function() {
		var a = document.createElement("div");
		a.innerHTML = "<table><tbody>" + this.content + "</tbody></table>";
		return $A(a.childNodes[0].childNodes[0].childNodes)
	}
};
var Insertion = new Object();
Insertion.Before = Class.create();
Insertion.Before.prototype = Object.extend(
		new Abstract.Insertion("beforeBegin"), {
			initializeRange : function() {
				this.range.setStartBefore(this.element)
			},
			insertContent : function(a) {
				a.each((function(b) {
					this.element.parentNode.insertBefore(b, this.element)
				}).bind(this))
			}
		});
Insertion.Top = Class.create();
Insertion.Top.prototype = Object.extend(new Abstract.Insertion("afterBegin"), {
	initializeRange : function() {
		this.range.selectNodeContents(this.element);
		this.range.collapse(true)
	},
	insertContent : function(a) {
		a.reverse(false).each((function(b) {
			this.element.insertBefore(b, this.element.firstChild)
		}).bind(this))
	}
});
Insertion.Bottom = Class.create();
Insertion.Bottom.prototype = Object.extend(new Abstract.Insertion("beforeEnd"),
		{
			initializeRange : function() {
				this.range.selectNodeContents(this.element);
				this.range.collapse(this.element)
			},
			insertContent : function(a) {
				a.each((function(b) {
					this.element.appendChild(b)
				}).bind(this))
			}
		});
Insertion.After = Class.create();
Insertion.After.prototype = Object.extend(new Abstract.Insertion("afterEnd"), {
	initializeRange : function() {
		this.range.setStartAfter(this.element)
	},
	insertContent : function(a) {
		a.each((function(b) {
			this.element.parentNode.insertBefore(b, this.element.nextSibling)
		}).bind(this))
	}
});
Element.ClassNames = Class.create();
Element.ClassNames.prototype = {
	initialize : function(a) {
		this.element = $(a)
	},
	_each : function(a) {
		this.element.className.split(/\s+/).select(function(b) {
			return b.length > 0
		})._each(a)
	},
	set : function(a) {
		this.element.className = a
	},
	add : function(a) {
		if (this.include(a)) {
			return
		}
		this.set($A(this).concat(a).join(" "))
	},
	remove : function(a) {
		if (!this.include(a)) {
			return
		}
		this.set($A(this).without(a).join(" "))
	},
	toString : function() {
		return $A(this).join(" ")
	}
};
Object.extend(Element.ClassNames.prototype, Enumerable);
var Selector = Class.create();
Selector.prototype = {
	initialize : function(a) {
		this.expression = a.strip();
		this.compileMatcher()
	},
	compileMatcher : function() {
		if (Prototype.BrowserFeatures.XPath
				&& !(/\[[\w-]*?:/).test(this.expression)) {
			return this.compileXPathMatcher()
		}
		var e = this.expression, ps = Selector.patterns, h = Selector.handlers, c = Selector.criteria, le, p, m;
		if (Selector._cache[e]) {
			this.matcher = Selector._cache[e];
			return
		}
		this.matcher = [ "this.matcher = function(root) {",
				"var r = root, h = Selector.handlers, c = false, n;" ];
		while (e && le != e && (/\S/).test(e)) {
			le = e;
			for ( var i in ps) {
				p = ps[i];
				if (m = e.match(p)) {
					this.matcher.push(typeof c[i] == "function" ? c[i](m)
							: new Template(c[i]).evaluate(m));
					e = e.replace(m[0], "");
					break
				}
			}
		}
		this.matcher.push("return h.unique(n);\n}");
		eval(this.matcher.join("\n"));
		Selector._cache[this.expression] = this.matcher
	},
	compileXPathMatcher : function() {
		var f = this.expression, g = Selector.patterns, b = Selector.xpath, d, a;
		if (Selector._cache[f]) {
			this.xpath = Selector._cache[f];
			return
		}
		this.matcher = [ ".//*" ];
		while (f && d != f && (/\S/).test(f)) {
			d = f;
			for ( var c in g) {
				if (a = f.match(g[c])) {
					this.matcher.push(typeof b[c] == "function" ? b[c](a)
							: new Template(b[c]).evaluate(a));
					f = f.replace(a[0], "");
					break
				}
			}
		}
		this.xpath = this.matcher.join("");
		Selector._cache[this.expression] = this.xpath
	},
	findElements : function(a) {
		a = a || document;
		if (this.xpath) {
			return document._getElementsByXPath(this.xpath, a)
		}
		return this.matcher(a)
	},
	match : function(a) {
		return this.findElements(document).include(a)
	},
	toString : function() {
		return this.expression
	},
	inspect : function() {
		return "#<Selector:" + this.expression.inspect() + ">"
	}
};
Object
		.extend(
				Selector,
				{
					_cache : {},
					xpath : {
						descendant : "//*",
						child : "/*",
						adjacent : "/following-sibling::*[1]",
						laterSibling : "/following-sibling::*",
						tagName : function(a) {
							if (a[1] == "*") {
								return ""
							}
							return "[local-name()='" + a[1].toLowerCase()
									+ "' or local-name()='"
									+ a[1].toUpperCase() + "']"
						},
						className : "[contains(concat(' ', @class, ' '), ' #{1} ')]",
						id : "[@id='#{1}']",
						attrPresence : "[@#{1}]",
						attr : function(a) {
							a[3] = a[5] || a[6];
							return new Template(Selector.xpath.operators[a[2]])
									.evaluate(a)
						},
						pseudo : function(a) {
							var b = Selector.xpath.pseudos[a[1]];
							if (!b) {
								return ""
							}
							if (typeof b === "function") {
								return b(a)
							}
							return new Template(Selector.xpath.pseudos[a[1]])
									.evaluate(a)
						},
						operators : {
							"=" : "[@#{1}='#{3}']",
							"!=" : "[@#{1}!='#{3}']",
							"^=" : "[starts-with(@#{1}, '#{3}')]",
							"$=" : "[substring(@#{1}, (string-length(@#{1}) - string-length('#{3}') + 1))='#{3}']",
							"*=" : "[contains(@#{1}, '#{3}')]",
							"~=" : "[contains(concat(' ', @#{1}, ' '), ' #{3} ')]",
							"|=" : "[contains(concat('-', @#{1}, '-'), '-#{3}-')]"
						},
						pseudos : {
							"first-child" : "[not(preceding-sibling::*)]",
							"last-child" : "[not(following-sibling::*)]",
							"only-child" : "[not(preceding-sibling::* or following-sibling::*)]",
							empty : "[count(*) = 0 and (count(text()) = 0 or translate(text(), ' \t\r\n', '') = '')]",
							checked : "[@checked]",
							disabled : "[@disabled]",
							enabled : "[not(@disabled)]",
							not : function(b) {
								var j = b[6], h = Selector.patterns, a = Selector.xpath, f, b, c;
								var g = [];
								while (j && f != j && (/\S/).test(j)) {
									f = j;
									for ( var d in h) {
										if (b = j.match(h[d])) {
											c = typeof a[d] == "function" ? a[d]
													(b)
													: new Template(a[d])
															.evaluate(b);
											g
													.push("("
															+ c
																	.substring(
																			1,
																			c.length - 1)
															+ ")");
											j = j.replace(b[0], "");
											break
										}
									}
								}
								return "[not(" + g.join(" and ") + ")]"
							},
							"nth-child" : function(a) {
								return Selector.xpath.pseudos.nth(
										"(count(./preceding-sibling::*) + 1) ",
										a)
							},
							"nth-last-child" : function(a) {
								return Selector.xpath.pseudos.nth(
										"(count(./following-sibling::*) + 1) ",
										a)
							},
							"nth-of-type" : function(a) {
								return Selector.xpath.pseudos.nth(
										"position() ", a)
							},
							"nth-last-of-type" : function(a) {
								return Selector.xpath.pseudos.nth(
										"(last() + 1 - position()) ", a)
							},
							"first-of-type" : function(a) {
								a[6] = "1";
								return Selector.xpath.pseudos["nth-of-type"](a)
							},
							"last-of-type" : function(a) {
								a[6] = "1";
								return Selector.xpath.pseudos["nth-last-of-type"]
										(a)
							},
							"only-of-type" : function(a) {
								var b = Selector.xpath.pseudos;
								return b["first-of-type"](a)
										+ b["last-of-type"](a)
							},
							nth : function(g, e) {
								var h, i = e[6], d;
								if (i == "even") {
									i = "2n+0"
								}
								if (i == "odd") {
									i = "2n+1"
								}
								if (h = i.match(/^(\d+)$/)) {
									return "[" + g + "= " + h[1] + "]"
								}
								if (h = i.match(/^(-?\d*)?n(([+-])(\d+))?/)) {
									if (h[1] == "-") {
										h[1] = -1
									}
									var f = h[1] ? Number(h[1]) : 1;
									var c = h[2] ? Number(h[2]) : 0;
									d = "[((#{fragment} - #{b}) mod #{a} = 0) and ((#{fragment} - #{b}) div #{a} >= 0)]";
									return new Template(d).evaluate( {
										fragment : g,
										a : f,
										b : c
									})
								}
							}
						}
					},
					criteria : {
						tagName : 'n = h.tagName(n, r, "#{1}", c);   c = false;',
						className : 'n = h.className(n, r, "#{1}", c); c = false;',
						id : 'n = h.id(n, r, "#{1}", c);        c = false;',
						attrPresence : 'n = h.attrPresence(n, r, "#{1}"); c = false;',
						attr : function(a) {
							a[3] = (a[5] || a[6]);
							return new Template(
									'n = h.attr(n, r, "#{1}", "#{3}", "#{2}"); c = false;')
									.evaluate(a)
						},
						pseudo : function(a) {
							if (a[6]) {
								a[6] = a[6].replace(/"/g, '\\"')
							}
							return new Template(
									'n = h.pseudo(n, "#{1}", "#{6}", r, c); c = false;')
									.evaluate(a)
						},
						descendant : 'c = "descendant";',
						child : 'c = "child";',
						adjacent : 'c = "adjacent";',
						laterSibling : 'c = "laterSibling";'
					},
					patterns : {
						laterSibling : /^\s*~\s*/,
						child : /^\s*>\s*/,
						adjacent : /^\s*\+\s*/,
						descendant : /^\s/,
						tagName : /^\s*(\*|[\w\-]+)(\b|$)?/,
						id : /^#([\w\-\*]+)(\b|$)/,
						className : /^\.([\w\-\*]+)(\b|$)/,
						pseudo : /^:((first|last|nth|nth-last|only)(-child|-of-type)|empty|checked|(en|dis)abled|not)(\((.*?)\))?(\b|$|\s|(?=:))/,
						attrPresence : /^\[([\w]+)\]/,
						attr : /\[((?:[\w-]*:)?[\w-]+)\s*(?:([!^$*~|]?=)\s*((['"])([^\]]*?)\4|([^'"][^\]]*?)))?\]/
					},
					handlers : {
						concat : function(d, c) {
							for ( var e = 0, f; f = c[e]; e++) {
								d.push(f)
							}
							return d
						},
						mark : function(a) {
							for ( var b = 0, c; c = a[b]; b++) {
								c._counted = true
							}
							return a
						},
						unmark : function(a) {
							for ( var b = 0, c; c = a[b]; b++) {
								c._counted = undefined
							}
							return a
						},
						index : function(a, d, f) {
							a._counted = true;
							if (d) {
								for ( var b = a.childNodes, e = b.length - 1, c = 1; e >= 0; e--) {
									node = b[e];
									if (node.nodeType == 1
											&& (!f || node._counted)) {
										node.nodeIndex = c++
									}
								}
							} else {
								for ( var e = 0, c = 1, b = a.childNodes; node = b[e]; e++) {
									if (node.nodeType == 1
											&& (!f || node._counted)) {
										node.nodeIndex = c++
									}
								}
							}
						},
						unique : function(b) {
							if (b.length == 0) {
								return b
							}
							var d = [], e;
							for ( var c = 0, a = b.length; c < a; c++) {
								if (!(e = b[c])._counted) {
									e._counted = true;
									d.push(Element.extend(e))
								}
							}
							return Selector.handlers.unmark(d)
						},
						descendant : function(a) {
							var d = Selector.handlers;
							for ( var c = 0, b = [], e; e = a[c]; c++) {
								d.concat(b, e.getElementsByTagName("*"))
							}
							return b
						},
						child : function(a) {
							var f = Selector.handlers;
							for ( var e = 0, d = [], g; g = a[e]; e++) {
								for ( var b = 0, c = [], k; k = g.childNodes[b]; b++) {
									if (k.nodeType == 1 && k.tagName != "!") {
										d.push(k)
									}
								}
							}
							return d
						},
						adjacent : function(a) {
							for ( var c = 0, b = [], e; e = a[c]; c++) {
								var d = this.nextElementSibling(e);
								if (d) {
									b.push(d)
								}
							}
							return b
						},
						laterSibling : function(a) {
							var d = Selector.handlers;
							for ( var c = 0, b = [], e; e = a[c]; c++) {
								d.concat(b, Element.nextSiblings(e))
							}
							return b
						},
						nextElementSibling : function(a) {
							while (a = a.nextSibling) {
								if (a.nodeType == 1) {
									return a
								}
							}
							return null
						},
						previousElementSibling : function(a) {
							while (a = a.previousSibling) {
								if (a.nodeType == 1) {
									return a
								}
							}
							return null
						},
						tagName : function(b, a, e, j) {
							e = e.toUpperCase();
							var d = [], f = Selector.handlers;
							if (b) {
								if (j) {
									if (j == "descendant") {
										for ( var c = 0, g; g = b[c]; c++) {
											f.concat(d, g
													.getElementsByTagName(e))
										}
										return d
									} else {
										b = this[j](b)
									}
									if (e == "*") {
										return b
									}
								}
								for ( var c = 0, g; g = b[c]; c++) {
									if (g.tagName.toUpperCase() == e) {
										d.push(g)
									}
								}
								return d
							} else {
								return a.getElementsByTagName(e)
							}
						},
						id : function(b, a, j, f) {
							var g = $(j), d = Selector.handlers;
							if (!b && a == document) {
								return g ? [ g ] : []
							}
							if (b) {
								if (f) {
									if (f == "child") {
										for ( var c = 0, e; e = b[c]; c++) {
											if (g.parentNode == e) {
												return [ g ]
											}
										}
									} else {
										if (f == "descendant") {
											for ( var c = 0, e; e = b[c]; c++) {
												if (Element.descendantOf(g, e)) {
													return [ g ]
												}
											}
										} else {
											if (f == "adjacent") {
												for ( var c = 0, e; e = b[c]; c++) {
													if (Selector.handlers
															.previousElementSibling(g) == e) {
														return [ g ]
													}
												}
											} else {
												b = d[f](b)
											}
										}
									}
								}
								for ( var c = 0, e; e = b[c]; c++) {
									if (e == g) {
										return [ g ]
									}
								}
								return []
							}
							return (g && Element.descendantOf(g, a)) ? [ g ]
									: []
						},
						className : function(b, a, c, d) {
							if (b && d) {
								b = this[d](b)
							}
							return Selector.handlers.byClassName(b, a, c)
						},
						byClassName : function(c, b, f) {
							if (!c) {
								c = Selector.handlers.descendant( [ b ])
							}
							var h = " " + f + " ";
							for ( var e = 0, d = [], g, a; g = c[e]; e++) {
								a = g.className;
								if (a.length == 0) {
									continue
								}
								if (a == f || (" " + a + " ").include(h)) {
									d.push(g)
								}
							}
							return d
						},
						attrPresence : function(c, b, a) {
							var e = [];
							for ( var d = 0, f; f = c[d]; d++) {
								if (Element.hasAttribute(f, a)) {
									e.push(f)
								}
							}
							return e
						},
						attr : function(a, h, g, j, b) {
							if (!a) {
								a = h.getElementsByTagName("*")
							}
							var k = Selector.operators[b], d = [];
							for ( var e = 0, c; c = a[e]; e++) {
								var f = Element.readAttribute(c, g);
								if (f === null) {
									continue
								}
								if (k(f, j)) {
									d.push(c)
								}
							}
							return d
						},
						pseudo : function(b, c, e, a, d) {
							if (b && d) {
								b = this[d](b)
							}
							if (!b) {
								b = a.getElementsByTagName("*")
							}
							return Selector.pseudos[c](b, e, a)
						}
					},
					pseudos : {
						"first-child" : function(b, f, a) {
							for ( var d = 0, c = [], e; e = b[d]; d++) {
								if (Selector.handlers.previousElementSibling(e)) {
									continue
								}
								c.push(e)
							}
							return c
						},
						"last-child" : function(b, f, a) {
							for ( var d = 0, c = [], e; e = b[d]; d++) {
								if (Selector.handlers.nextElementSibling(e)) {
									continue
								}
								c.push(e)
							}
							return c
						},
						"only-child" : function(b, g, a) {
							var e = Selector.handlers;
							for ( var d = 0, c = [], f; f = b[d]; d++) {
								if (!e.previousElementSibling(f)
										&& !e.nextElementSibling(f)) {
									c.push(f)
								}
							}
							return c
						},
						"nth-child" : function(b, c, a) {
							return Selector.pseudos.nth(b, c, a)
						},
						"nth-last-child" : function(b, c, a) {
							return Selector.pseudos.nth(b, c, a, true)
						},
						"nth-of-type" : function(b, c, a) {
							return Selector.pseudos.nth(b, c, a, false, true)
						},
						"nth-last-of-type" : function(b, c, a) {
							return Selector.pseudos.nth(b, c, a, true, true)
						},
						"first-of-type" : function(b, c, a) {
							return Selector.pseudos.nth(b, "1", a, false, true)
						},
						"last-of-type" : function(b, c, a) {
							return Selector.pseudos.nth(b, "1", a, true, true)
						},
						"only-of-type" : function(b, d, a) {
							var c = Selector.pseudos;
							return c["last-of-type"](c["first-of-type"]
									(b, d, a), d, a)
						},
						getIndices : function(d, c, e) {
							if (d == 0) {
								return c > 0 ? [ c ] : []
							}
							return $R(1, e).inject( [], function(a, b) {
								if (0 == (b - c) % d && (b - c) / d >= 0) {
									a.push(b)
								}
								return a
							})
						},
						nth : function(c, s, u, r, e) {
							if (c.length == 0) {
								return []
							}
							if (s == "even") {
								s = "2n+0"
							}
							if (s == "odd") {
								s = "2n+1"
							}
							var q = Selector.handlers, p = [], d = [], g;
							q.mark(c);
							for ( var o = 0, f; f = c[o]; o++) {
								if (!f.parentNode._counted) {
									q.index(f.parentNode, r, e);
									d.push(f.parentNode)
								}
							}
							if (s.match(/^\d+$/)) {
								s = Number(s);
								for ( var o = 0, f; f = c[o]; o++) {
									if (f.nodeIndex == s) {
										p.push(f)
									}
								}
							} else {
								if (g = s.match(/^(-?\d*)?n(([+-])(\d+))?/)) {
									if (g[1] == "-") {
										g[1] = -1
									}
									var v = g[1] ? Number(g[1]) : 1;
									var t = g[2] ? Number(g[2]) : 0;
									var w = Selector.pseudos.getIndices(v, t,
											c.length);
									for ( var o = 0, f, k = w.length; f = c[o]; o++) {
										for ( var n = 0; n < k; n++) {
											if (f.nodeIndex == w[n]) {
												p.push(f)
											}
										}
									}
								}
							}
							q.unmark(c);
							q.unmark(d);
							return p
						},
						empty : function(b, f, a) {
							for ( var d = 0, c = [], e; e = b[d]; d++) {
								if (e.tagName == "!"
										|| (e.firstChild && !e.innerHTML
												.match(/^\s*$/))) {
									continue
								}
								c.push(e)
							}
							return c
						},
						not : function(a, d, k) {
							var g = Selector.handlers, l, c;
							var j = new Selector(d).findElements(k);
							g.mark(j);
							for ( var f = 0, e = [], b; b = a[f]; f++) {
								if (!b._counted) {
									e.push(b)
								}
							}
							g.unmark(j);
							return e
						},
						enabled : function(b, f, a) {
							for ( var d = 0, c = [], e; e = b[d]; d++) {
								if (!e.disabled) {
									c.push(e)
								}
							}
							return c
						},
						disabled : function(b, f, a) {
							for ( var d = 0, c = [], e; e = b[d]; d++) {
								if (e.disabled) {
									c.push(e)
								}
							}
							return c
						},
						checked : function(b, f, a) {
							for ( var d = 0, c = [], e; e = b[d]; d++) {
								if (e.checked) {
									c.push(e)
								}
							}
							return c
						}
					},
					operators : {
						"=" : function(b, a) {
							return b == a
						},
						"!=" : function(b, a) {
							return b != a
						},
						"^=" : function(b, a) {
							return b.startsWith(a)
						},
						"$=" : function(b, a) {
							return b.endsWith(a)
						},
						"*=" : function(b, a) {
							return b.include(a)
						},
						"~=" : function(b, a) {
							return (" " + b + " ").include(" " + a + " ")
						},
						"|=" : function(b, a) {
							return ("-" + b.toUpperCase() + "-").include("-"
									+ a.toUpperCase() + "-")
						}
					},
					matchElements : function(f, g) {
						var e = new Selector(g).findElements(), d = Selector.handlers;
						d.mark(e);
						for ( var c = 0, b = [], a; a = f[c]; c++) {
							if (a._counted) {
								b.push(a)
							}
						}
						d.unmark(e);
						return b
					},
					findElement : function(b, c, a) {
						if (typeof c == "number") {
							a = c;
							c = false
						}
						return Selector.matchElements(b, c || "*")[a || 0]
					},
					findChildElements : function(e, g) {
						var j = g.join(","), g = [];
						j.scan(/(([\w#:.~>+()\s-]+|\*|\[.*?\])+)\s*(,|$)/,
								function(h) {
									g.push(h[1].strip())
								});
						var d = [], f = Selector.handlers;
						for ( var c = 0, b = g.length, a; c < b; c++) {
							a = new Selector(g[c].strip());
							f.concat(d, a.findElements(e))
						}
						return (b > 1) ? f.unique(d) : d
					}
				});
function $$() {
	return Selector.findChildElements(document, $A(arguments))
}
var Form = {
	reset : function(a) {
		$(a).reset();
		return a
	},
	serializeElements : function(c, a) {
		var b = c.inject( {}, function(d, f) {
			if (!f.disabled && f.name) {
				var e = f.name, g = $(f).getValue();
				if (g != null) {
					if (e in d) {
						if (d[e].constructor != Array) {
							d[e] = [ d[e] ]
						}
						d[e].push(g)
					} else {
						d[e] = g
					}
				}
			}
			return d
		});
		return a ? b : Hash.toQueryString(b)
	}
};
Form.Methods = {
	serialize : function(b, a) {
		return Form.serializeElements(Form.getElements(b), a)
	},
	getElements : function(a) {
		return $A($(a).getElementsByTagName("*")).inject( [], function(b, c) {
			if (Form.Element.Serializers[c.tagName.toLowerCase()]) {
				b.push(Element.extend(c))
			}
			return b
		})
	},
	getInputs : function(g, c, d) {
		g = $(g);
		var a = g.getElementsByTagName("input");
		if (!c && !d) {
			return $A(a).map(Element.extend)
		}
		for ( var e = 0, h = [], f = a.length; e < f; e++) {
			var b = a[e];
			if ((c && b.type != c) || (d && b.name != d)) {
				continue
			}
			h.push(Element.extend(b))
		}
		return h
	},
	disable : function(a) {
		a = $(a);
		Form.getElements(a).invoke("disable");
		return a
	},
	enable : function(a) {
		a = $(a);
		Form.getElements(a).invoke("enable");
		return a
	},
	findFirstElement : function(a) {
		return $(a).getElements().find(
				function(b) {
					return b.type != "hidden"
							&& !b.disabled
							&& [ "input", "select", "textarea" ]
									.include(b.tagName.toLowerCase())
				})
	},
	focusFirstElement : function(a) {
		a = $(a);
		a.findFirstElement().activate();
		return a
	},
	request : function(b, a) {
		b = $(b), a = Object.clone(a || {});
		var c = a.parameters;
		a.parameters = b.serialize(true);
		if (c) {
			if (typeof c == "string") {
				c = c.toQueryParams()
			}
			Object.extend(a.parameters, c)
		}
		if (b.hasAttribute("method") && !a.method) {
			a.method = b.method
		}
		return new Ajax.Request(b.readAttribute("action"), a)
	}
};
Form.Element = {
	focus : function(a) {
		$(a).focus();
		return a
	},
	select : function(a) {
		$(a).select();
		return a
	}
};
Form.Element.Methods = {
	serialize : function(a) {
		a = $(a);
		if (!a.disabled && a.name) {
			var b = a.getValue();
			if (b != undefined) {
				var c = {};
				c[a.name] = b;
				return Hash.toQueryString(c)
			}
		}
		return ""
	},
	getValue : function(a) {
		a = $(a);
		var b = a.tagName.toLowerCase();
		return Form.Element.Serializers[b](a)
	},
	clear : function(a) {
		$(a).value = "";
		return a
	},
	present : function(a) {
		return $(a).value != ""
	},
	activate : function(a) {
		a = $(a);
		try {
			a.focus();
			if (a.select
					&& (a.tagName.toLowerCase() != "input" || ! [ "button",
							"reset", "submit" ].include(a.type))) {
				a.select()
			}
		} catch (b) {
		}
		return a
	},
	disable : function(a) {
		a = $(a);
		a.blur();
		a.disabled = true;
		return a
	},
	enable : function(a) {
		a = $(a);
		a.disabled = false;
		return a
	}
};
var Field = Form.Element;
var $F = Form.Element.Methods.getValue;
Form.Element.Serializers = {
	input : function(a) {
		switch (a.type.toLowerCase()) {
		case "checkbox":
		case "radio":
			return Form.Element.Serializers.inputSelector(a);
		default:
			return Form.Element.Serializers.textarea(a)
		}
	},
	inputSelector : function(a) {
		return a.checked ? a.value : null
	},
	textarea : function(a) {
		return a.value
	},
	select : function(a) {
		return this[a.type == "select-one" ? "selectOne" : "selectMany"](a)
	},
	selectOne : function(b) {
		var a = b.selectedIndex;
		return a >= 0 ? this.optionValue(b.options[a]) : null
	},
	selectMany : function(d) {
		var a, e = d.length;
		if (!e) {
			return null
		}
		for ( var c = 0, a = []; c < e; c++) {
			var b = d.options[c];
			if (b.selected) {
				a.push(this.optionValue(b))
			}
		}
		return a
	},
	optionValue : function(a) {
		return Element.extend(a).hasAttribute("value") ? a.value : a.text
	}
};
Abstract.TimedObserver = function() {
};
Abstract.TimedObserver.prototype = {
	initialize : function(a, b, c) {
		this.frequency = b;
		this.element = $(a);
		this.callback = c;
		this.lastValue = this.getValue();
		this.registerCallback()
	},
	registerCallback : function() {
		setInterval(this.onTimerEvent.bind(this), this.frequency * 1000)
	},
	onTimerEvent : function() {
		var a = this.getValue();
		var b = ("string" == typeof this.lastValue && "string" == typeof a ? this.lastValue != a
				: String(this.lastValue) != String(a));
		if (b) {
			this.callback(this.element, a);
			this.lastValue = a
		}
	}
};
Form.Element.Observer = Class.create();
Form.Element.Observer.prototype = Object.extend(new Abstract.TimedObserver(), {
	getValue : function() {
		return Form.Element.getValue(this.element)
	}
});
Form.Observer = Class.create();
Form.Observer.prototype = Object.extend(new Abstract.TimedObserver(), {
	getValue : function() {
		return Form.serialize(this.element)
	}
});
Abstract.EventObserver = function() {
};
Abstract.EventObserver.prototype = {
	initialize : function(a, b) {
		this.element = $(a);
		this.callback = b;
		this.lastValue = this.getValue();
		if (this.element.tagName.toLowerCase() == "form") {
			this.registerFormCallbacks()
		} else {
			this.registerCallback(this.element)
		}
	},
	onElementEvent : function() {
		var a = this.getValue();
		if (this.lastValue != a) {
			this.callback(this.element, a);
			this.lastValue = a
		}
	},
	registerFormCallbacks : function() {
		Form.getElements(this.element).each(this.registerCallback.bind(this))
	},
	registerCallback : function(a) {
		if (a.type) {
			switch (a.type.toLowerCase()) {
			case "checkbox":
			case "radio":
				Event.observe(a, "click", this.onElementEvent.bind(this));
				break;
			default:
				Event.observe(a, "change", this.onElementEvent.bind(this));
				break
			}
		}
	}
};
Form.Element.EventObserver = Class.create();
Form.Element.EventObserver.prototype = Object.extend(
		new Abstract.EventObserver(), {
			getValue : function() {
				return Form.Element.getValue(this.element)
			}
		});
Form.EventObserver = Class.create();
Form.EventObserver.prototype = Object.extend(new Abstract.EventObserver(), {
	getValue : function() {
		return Form.serialize(this.element)
	}
});
if (!window.Event) {
	var Event = new Object()
}
Object
		.extend(
				Event,
				{
					KEY_BACKSPACE : 8,
					KEY_TAB : 9,
					KEY_RETURN : 13,
					KEY_ESC : 27,
					KEY_LEFT : 37,
					KEY_UP : 38,
					KEY_RIGHT : 39,
					KEY_DOWN : 40,
					KEY_DELETE : 46,
					KEY_HOME : 36,
					KEY_END : 35,
					KEY_PAGEUP : 33,
					KEY_PAGEDOWN : 34,
					element : function(a) {
						return $(a.target || a.srcElement)
					},
					isLeftClick : function(a) {
						return (((a.which) && (a.which == 1)) || ((a.button) && (a.button == 1)))
					},
					pointerX : function(a) {
						return a.pageX
								|| (a.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft))
					},
					pointerY : function(a) {
						return a.pageY
								|| (a.clientY + (document.documentElement.scrollTop || document.body.scrollTop))
					},
					stop : function(a) {
						if (a.preventDefault) {
							a.preventDefault();
							a.stopPropagation()
						} else {
							a.returnValue = false;
							a.cancelBubble = true
						}
					},
					findElement : function(c, b) {
						var a = Event.element(c);
						while (a.parentNode
								&& (!a.tagName || (a.tagName.toUpperCase() != b
										.toUpperCase()))) {
							a = a.parentNode
						}
						return a
					},
					observers : false,
					_observeAndCache : function(d, c, b, a) {
						if (!this.observers) {
							this.observers = []
						}
						if (d.addEventListener) {
							this.observers.push( [ d, c, b, a ]);
							d.addEventListener(c, b, a)
						} else {
							if (d.attachEvent) {
								this.observers.push( [ d, c, b, a ]);
								d.attachEvent("on" + c, b)
							}
						}
					},
					unloadCache : function() {
						if (!Event.observers) {
							return
						}
						for ( var a = 0, b = Event.observers.length; a < b; a++) {
							Event.stopObserving.apply(this, Event.observers[a]);
							Event.observers[a][0] = null
						}
						Event.observers = false
					},
					observe : function(d, c, b, a) {
						d = $(d);
						a = a || false;
						if (c == "keypress"
								&& (Prototype.Browser.WebKit || d.attachEvent)) {
							c = "keydown"
						}
						Event._observeAndCache(d, c, b, a)
					},
					stopObserving : function(d, c, b, a) {
						d = $(d);
						a = a || false;
						if (c == "keypress"
								&& (Prototype.Browser.WebKit || d.attachEvent)) {
							c = "keydown"
						}
						if (d.removeEventListener) {
							d.removeEventListener(c, b, a)
						} else {
							if (d.detachEvent) {
								try {
									d.detachEvent("on" + c, b)
								} catch (f) {
								}
							}
						}
					}
				});
if (Prototype.Browser.IE) {
	Event.observe(window, "unload", Event.unloadCache, false)
}
var Position = {
	includeScrollOffsets : false,
	prepare : function() {
		this.deltaX = window.pageXOffset || document.documentElement.scrollLeft
				|| document.body.scrollLeft || 0;
		this.deltaY = window.pageYOffset || document.documentElement.scrollTop
				|| document.body.scrollTop || 0
	},
	realOffset : function(b) {
		var a = 0, c = 0;
		do {
			a += b.scrollTop || 0;
			c += b.scrollLeft || 0;
			b = b.parentNode
		} while (b);
		return [ c, a ]
	},
	cumulativeOffset : function(b) {
		var a = 0, c = 0;
		do {
			a += b.offsetTop || 0;
			c += b.offsetLeft || 0;
			b = b.offsetParent
		} while (b);
		return [ c, a ]
	},
	positionedOffset : function(b) {
		var a = 0, d = 0;
		do {
			a += b.offsetTop || 0;
			d += b.offsetLeft || 0;
			b = b.offsetParent;
			if (b) {
				if (b.tagName == "BODY") {
					break
				}
				var c = Element.getStyle(b, "position");
				if (c == "relative" || c == "absolute") {
					break
				}
			}
		} while (b);
		return [ d, a ]
	},
	offsetParent : function(a) {
		if (a.offsetParent) {
			return a.offsetParent
		}
		if (a == document.body) {
			return a
		}
		while ((a = a.parentNode) && a != document.body) {
			if (Element.getStyle(a, "position") != "static") {
				return a
			}
		}
		return document.body
	},
	within : function(b, a, c) {
		if (this.includeScrollOffsets) {
			return this.withinIncludingScrolloffsets(b, a, c)
		}
		this.xcomp = a;
		this.ycomp = c;
		this.offset = this.cumulativeOffset(b);
		return (c >= this.offset[1] && c < this.offset[1] + b.offsetHeight
				&& a >= this.offset[0] && a < this.offset[0] + b.offsetWidth)
	},
	withinIncludingScrolloffsets : function(b, a, d) {
		var c = this.realOffset(b);
		this.xcomp = a + c[0] - this.deltaX;
		this.ycomp = d + c[1] - this.deltaY;
		this.offset = this.cumulativeOffset(b);
		return (this.ycomp >= this.offset[1]
				&& this.ycomp < this.offset[1] + b.offsetHeight
				&& this.xcomp >= this.offset[0] && this.xcomp < this.offset[0]
				+ b.offsetWidth)
	},
	overlap : function(b, a) {
		if (!b) {
			return 0
		}
		if (b == "vertical") {
			return ((this.offset[1] + a.offsetHeight) - this.ycomp)
					/ a.offsetHeight
		}
		if (b == "horizontal") {
			return ((this.offset[0] + a.offsetWidth) - this.xcomp)
					/ a.offsetWidth
		}
	},
	page : function(d) {
		var a = 0, c = 0;
		var b = d;
		do {
			a += b.offsetTop || 0;
			c += b.offsetLeft || 0;
			if (b.offsetParent == document.body) {
				if (Element.getStyle(b, "position") == "absolute") {
					break
				}
			}
		} while (b = b.offsetParent);
		b = d;
		do {
			if (!window.opera || b.tagName == "BODY") {
				a -= b.scrollTop || 0;
				c -= b.scrollLeft || 0
			}
		} while (b = b.parentNode);
		return [ c, a ]
	},
	clone : function(c, e) {
		var a = Object.extend( {
			setLeft : true,
			setTop : true,
			setWidth : true,
			setHeight : true,
			offsetTop : 0,
			offsetLeft : 0
		}, arguments[2] || {});
		c = $(c);
		var d = Position.page(c);
		e = $(e);
		var f = [ 0, 0 ];
		var b = null;
		if (Element.getStyle(e, "position") == "absolute") {
			b = Position.offsetParent(e);
			f = Position.page(b)
		}
		if (b == document.body) {
			f[0] -= document.body.offsetLeft;
			f[1] -= document.body.offsetTop
		}
		if (a.setLeft) {
			e.style.left = (d[0] - f[0] + a.offsetLeft) + "px"
		}
		if (a.setTop) {
			e.style.top = (d[1] - f[1] + a.offsetTop) + "px"
		}
		if (a.setWidth) {
			e.style.width = c.offsetWidth + "px"
		}
		if (a.setHeight) {
			e.style.height = c.offsetHeight + "px"
		}
	},
	absolutize : function(b) {
		b = $(b);
		if (b.style.position == "absolute") {
			return
		}
		Position.prepare();
		var d = Position.positionedOffset(b);
		var f = d[1];
		var e = d[0];
		var c = b.clientWidth;
		var a = b.clientHeight;
		b._originalLeft = e - parseFloat(b.style.left || 0);
		b._originalTop = f - parseFloat(b.style.top || 0);
		b._originalWidth = b.style.width;
		b._originalHeight = b.style.height;
		b.style.position = "absolute";
		b.style.top = f + "px";
		b.style.left = e + "px";
		b.style.width = c + "px";
		b.style.height = a + "px"
	},
	relativize : function(a) {
		a = $(a);
		if (a.style.position == "relative") {
			return
		}
		Position.prepare();
		a.style.position = "relative";
		var c = parseFloat(a.style.top || 0) - (a._originalTop || 0);
		var b = parseFloat(a.style.left || 0) - (a._originalLeft || 0);
		a.style.top = c + "px";
		a.style.left = b + "px";
		a.style.height = a._originalHeight;
		a.style.width = a._originalWidth
	}
};
if (Prototype.Browser.WebKit) {
	Position.cumulativeOffset = function(b) {
		var a = 0, c = 0;
		do {
			a += b.offsetTop || 0;
			c += b.offsetLeft || 0;
			if (b.offsetParent == document.body) {
				if (Element.getStyle(b, "position") == "absolute") {
					break
				}
			}
			b = b.offsetParent
		} while (b);
		return [ c, a ]
	}
}
Element.addMethods();