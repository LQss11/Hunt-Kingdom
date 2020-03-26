	var locations = [
	
	["<div class='location-marker'><ul class='eco_admin_info'><li><i class='fa fa-paper-plane' aria-hidden='true'></i><p>156 Gramo Town North Street</p></li><li><i class='fa fa-phone' aria-hidden='true'></i><p>0800 - 3277 - 2808</p></li><li><i class='fa fa-envelope' aria-hidden='true'></i><p>eco@domain.com</p></li></ul></div>",40.79728,-74.48138],

	["<div class='location-marker'><ul class='eco_admin_info'><li><i class='fa fa-paper-plane' aria-hidden='true'></i><p>156 Gramo Town North Street</p></li><li><i class='fa fa-phone' aria-hidden='true'></i><p>0800 - 3277 - 2808</p></li><li><i class='fa fa-envelope' aria-hidden='true'></i><p>eco@domain.com</p></li></ul></div>",40.84755,-75.29140],

	["<div class='location-marker'><ul class='eco_admin_info'><li><i class='fa fa-paper-plane' aria-hidden='true'></i><p>156 Gramo Town North Street</p></li><li><i class='fa fa-phone' aria-hidden='true'></i><p>0800 - 3277 - 2808</p></li><li><i class='fa fa-envelope' aria-hidden='true'></i><p>eco@domain.com</p></li></ul></div>",40.73566,-74.17237],
	
	];
	// Setup the different icons and shadows
	var iconURLPrefix = 'images';
	var icons = [
		'images/marker-img-01.png', 
		'images/marker-img-02.png', 
		'images/marker-img-03.png', 			
		];
	var icons_length = icons.length;
	var shadow = {
	  anchor: new google.maps.Point(40.78902,-74.47236),
	  url: iconURLPrefix + 'msmarker.shadow.png'
	};

	var myOptions = {
	  center: new google.maps.LatLng(40.79676,-74.48066),
	  styles : [{featureType:'all',stylers:[{saturation:-100},{gamma:0.0}]}],
	  mapTypeId: 'roadmap',
	  mapTypeControl: true,
	  streetViewControl: true,
	  panControl: true,
	  scrollwheel: false,
	  draggable: true,
	   zoom: 10

	}
	var map = new google.maps.Map(document.getElementById("eco_map_marker"), myOptions);
	// A new Info Window is created and set content
  	var infowindow = new google.maps.InfoWindow({
    locations: locations,

    // Assign a maximum value for the width of the infowindow allows
    // greater control over the various content elements
    maxWidth: 350
  	});
	var marker;
	var markers = new Array();
	var iconCounter = 0;

	// Add the markers and infowindows to the map
	for (var i = 0; i < locations.length; i++) {  
	  marker = new google.maps.Marker({
		position: new google.maps.LatLng(locations[i][1], locations[i][2]),
		map: map,
		icon : icons[iconCounter],
		shadow: shadow
	  });

	  markers.push(marker);
	  google.maps.event.addListener(marker, 'click', (function(marker, i) {
		return function() {
		  infowindow.setContent(locations[i][0]);
		  infowindow.open(map, marker);
		}
	  })(marker, i));
	  
	  iconCounter++;
	  // We only have a limited number of possible icon colors, so we may have to restart the counter
	  if(iconCounter >= icons_length){
		iconCounter = 0;
	  }
	}
	
	  // *
  // START INFOWINDOW CUSTOMIZE.
  // The google.maps.event.addListener() event expects
  // the creation of the infowindow HTML structure 'domready'
  // and before the opening of the infowindow, defined styles are applied.
  // *
  google.maps.event.addListener(infowindow, 'domready', function() {

    // Reference to the DIV that wraps the bottom of infowindow
    var iwOuter = $('.gm-style-iw').css('left:-70px', 'top:30px');

    /* Since this div is in a position prior to .gm-div style-iw.
     * We use jQuery and create a iwBackground variable,
     * and took advantage of the existing reference .gm-style-iw for the previous div with .prev().
    */
    var iwBackground = iwOuter.prev();

    // Removes background shadow DIV
    iwBackground.children(':nth-child(2)').css({'display' : 'none'});

    // Removes white background DIV
    iwBackground.children(':nth-child(4)').css({'display' : 'none'});

    // Moves the infowindow 115px to the right.
    iwOuter.parent().parent().css({left: '15px' , top: '0px'});

    // Moves the shadow of the arrow 76px to the left margin.
    iwBackground.children(':nth-child(1)').attr('style', function(i,s){ return s + 'left: 20px !important;'});

    // Moves the arrow 76px to the left margin.
    iwBackground.children(':nth-child(3)').attr('style', function(i,s){ return s + 'left: 145px !important;' });

    // Changes the desired tail shadow color.
    iwBackground.children(':nth-child(3)').find('div').children().css({ 'z-index' : '1'});

    // Reference to the div that groups the close button elements.
    var iwCloseBtn = iwOuter.next();

    // Apply the desired effect to the close button
    iwCloseBtn.css({opacity: '1', right: '50px', top: '36px'/*border: '7px solid #48b5e9', 'border-radius': '13px', 'box-shadow': '0 0 5px #3990B9'*/});

    // If the content of infowindow not exceed the set maximum height, then the gradient is removed.
    if($('.iw-content').height() < 140){
      $('.iw-bottom-gradient').css({display: 'none'});
    }

    // The API automatically applies 0.7 opacity to the button after the mouseout event. This function reverses this event to the desired value.
    iwCloseBtn.mouseout(function(){
      $(this).css({opacity: '1'});
    });
  });

















































































	
