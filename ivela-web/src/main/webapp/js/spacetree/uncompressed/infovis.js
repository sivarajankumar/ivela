var Infovis = {
	
	Accordion: {
		colorBackgroundSelected: '#78BA91',
		colorFontSelected: '#fff',
		colorBackground: '#7389AE',
		colorFont: '#fff',
		
		effects: {}
	},
	
	initLayout: function() {
		var size = Window.getSize();
		var header = $('header'), left = $('left'), infovisContainer = $('infovis');
		var headerOffset = header.getSize().y, leftOffset = left.getSize().x;

		var newStyles = {
			'height': Math.floor((size.y - headerOffset) / 1),
			'width' : Math.floor((size.x - leftOffset) / 1)
		};
/*		
		var newStyles = {
			'height': 400,
			'width' : 600
		};
/**/
		infovisContainer.setProperties(newStyles);
		infovisContainer.setStyles(newStyles);
		infovisContainer.setStyles({
			'position':'absolute',
			'top': headerOffset + 'px',
			'left': leftOffset + 'px'
		});
		left.setStyle('height', newStyles.height);
		var effectsArray = new Array();
		var elementsTotalHeight = 0;
		var _self = this;
		$$('#left .left-item').each(function (elem) {
			_self.Accordion.effects[elem.innerHTML] = new Fx.Morph(elem, {duration:300,
				 Transition:Fx.Transitions.Quart.easeOut});
			elementsTotalHeight += elem.offsetHeight;
		});
		$$('#left .small-title').each(function (elem) {
			elementsTotalHeight += elem.offsetHeight;
		});
		//this.initAccordion(newStyles.height - elementsTotalHeight);
	},
	
	initAccordion: function(height) {
		var _this = this;
		var accordion = new Accordion('div.left-item', 'div.contained-item', {
			'fixedHeight': height,
			onActive: function (elem) {
				var fx = _this.Accordion.effects[elem.innerHTML];
				var _self = _this;
				fx.start({
					'color': _self.Accordion.colorFontSelected,
					'background-color':_self.Accordion.colorBackgroundSelected
				});
			},
			onBackground: function (elem) {
				var fx = _this.Accordion.effects[elem.innerHTML];
				var _self = _this;
				fx.start({
					'color': _self.Accordion.colorFont,
					'background-color':_self.Accordion.colorBackground
				});
			}
		}, $('left'));
	}
};
