/*======================================================
Javascript custom functions
=======================================================*/
jQuery(document).ready(function ($) {
	'use strict'

	/* =======================================================================
		  		 Chosen Script 
	   =======================================================================
	*/	
		if($(".kode-selecter").length){
			$(".kode-selecter").chosen()
		}
	
	
	/*================================================
			Progress Bar bootstrap
	=================================================*/
	 $(window).resize(function() {
    	moveProgressBar();
	});

    // SIGNATURE PROGRESS
    function moveProgressBar() {
      console.log("moveProgressBar");
        var getPercent = ($('.progress-wrap').data('progress-percent') / 100);
        var getProgressWrapWidth = $('.progress-wrap').width();
        var progressTotal = getPercent * getProgressWrapWidth;
        var animationLength = 2500;
        
        // on page load, animate percentage bar to data percentage length
        // .stop() used to prevent animation queueing
        $('.progress-bar').stop().animate({
            width: progressTotal,
        }, animationLength
        );
    }
	/*
	  =======================================================================
		  		COUNT DOWN Script
	  =======================================================================*/

		if($('.countdown-timer-01').length){
	        $('.countdown-timer-01').downCount({ date: '08/08/2018 12:00:00', offset: +1 });
	  	}
/*
	  =======================================================================
		  		COUNT DOWN Script
	  =======================================================================*/

		if($('.countdown-timer-02').length){
	        $('.countdown-timer-02').downCount({ date: '08/08/2018 12:00:00', offset: +1 });
	  	}

/*
	  =======================================================================
		  		COUNT DOWN Script
	  =======================================================================*/

		if($('.countdown-timer-03').length){
	        $('.countdown-timer-03').downCount({ date: '08/08/2018 12:00:00', offset: +1 });
	  	}
/*
	  =======================================================================
		  		COUNT DOWN Script
	  =======================================================================*/

		if($('.countdown-timer-04').length){
	        $('.countdown-timer-04').downCount({ date: '08/08/2018 12:00:00', offset: +1 });
	  	}

	/*
	  =======================================================================
		  		COUNT Up Script
	  =======================================================================
	*/
		if($(".counter-up").length){
			$('.counter-up').spincrement({
				duration: 10000
			});
		}	
	/* ---------------------------------------------------------------------- */
	/*	DL Responsive Menu
	/* ---------------------------------------------------------------------- */
		if(typeof($.fn.dlmenu) == 'function'){
			$('#kode-responsive-navigation').each(function(){
				$(this).find('.dl-submenu').each(function(){
					if( $(this).siblings('a').attr('href') && $(this).siblings('a').attr('href') != '#' ){
						var parent_nav = $('<li class="menu-item kode-parent-menu"></li>');
						parent_nav.append($(this).siblings('a').clone());
						
						$(this).prepend(parent_nav);
					}
				});
				$(this).dlmenu();
			});
		}
	/*
	==============================================================
		Masonry  Script Start
	==============================================================
	*/
	// Initialize Masonry

	    if ($('.masonry').length) {
	        var container = document.querySelector('.masonry');
	        var msnry = new Masonry(container, {
	            itemSelector: '.masonry-item'
	        });

	        msnry.on('layoutComplete', function() {

	           /* mr_firstSectionHeight = $('.main-container section:nth-of-type(1)').outerHeight(true);*/

	            // Fix floating project filters to bottom of projects container

	            if ($('.filters.floating').length) {
	                setupFloatingProjectFilters();
	                updateFloatingFilters();
	                window.addEventListener("scroll", updateFloatingFilters, false);
	            }

	            $('.masonry').addClass('fadeIn');
	            $('.masonry-loader').addClass('fadeOut');
	            if ($('.masonryFlyIn').length) {
	               
	            }
	        });

	        msnry.layout();
	    }
    /*==============================================================
		bxslider  Script Start
	==============================================================
	*/
		if($(".bg-slider").length){
		    $('.bg-slider').bxSlider({
			  minSlides: 1,
			  maxSlides: 1,
			  ticker: true,
			  speed: 60000
			});
		}
		if($(".bxslider").length){
			$('.bxslider').bxSlider({
			  mode: 'fade',
			 auto:true
			});
		}
		if($(".bg-slider").length){	
			$('.bx-pager').bxSlider({
			  auto:true,
			  pagerCustom: '#bx-pager'
			});
		}

		if($(".promoted-slider").length){
			$('.promoted-slider').bxSlider({
				minSlides: 2,
				maxSlides: 3,
				slideMargin: 30,
				slideWidth: true,
				ticker: true,
				speed: 60000
			});
		}
		if($(".slider-blog").length){
			$('.slider-blog').slick({
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  arrows: true,
			  fade: true,
				   responsive: [
			    {
			      breakpoint: 1024,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1,
			        infinite: true,
			      }
			    },
			    {
			      breakpoint: 600,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    },
			    {
			      breakpoint: 480,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    }
			  ]
			});
		}
		if($(".product-slider").length){	
			$('.product-slider').bxSlider({
			  pagerCustom: '#product-pager'
			});
		}	
	/*================================================
		owlCarouse2 start
	=================================================*/
		if($('.kode_latest_news_carousel').length){
		   var owl = $(".kode_latest_news_carousel");
		   owl.owlCarousel({ 
		   autoWidth:true,
		   nav:true,
			navigation : true,
			autoPlay: 3000,    //Set AutoPlay to 3 seconds
			 itemsCustom : [
			[0, 1],
			[450, 1],
			[600, 2],
			[700, 2],
			[1000, 2],
			[1200, 3],
			[1400, 3],
			[1600, 3]
			 ]
		   });
		}	
	/*==============================================================
		slick  Script Start
	==============================================================
	*/
		if($(".slider-for").length){
			$('.slider-for').slick({
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  arrows: false,
			  fade: true,
			  asNavFor: '.slider-nav',
			     responsive: [
			    {
			      breakpoint: 1024,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1,
			        infinite: true,
			      }
			    },
			    {
			      breakpoint: 600,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    },
			    {
			      breakpoint: 480,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    }
			  ]
			});
		}
		if($(".slider-nav").length){	
			$('.slider-nav').slick({
			  slidesToShow: 3,
			  slidesToScroll: 1,
			  loop:true,
			  dots:true,
			  arrows: false,
			  asNavFor: '.slider-for',
			  centerMode: true,
			  focusOnSelect: true,
			   responsive: [
			    {
			      breakpoint: 1024,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1,
			        infinite: true,
			      }
			    },
			    {
			      breakpoint: 600,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    },
			    {
			      breakpoint: 480,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 3
			      }
			    }
			  ]
			});
		}	
		if($(".event_for").length){
			$('.event_for').slick({
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  arrows: false,
			  fade: true,
			  asNavFor: '.event_nav',
			    responsive: [
			    {
			      breakpoint: 1024,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1,
			        infinite: true,
			      }
			    },
			    {
			      breakpoint: 600,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    },
			    {
			      breakpoint: 480,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    }
			  ]
			});
		}
		if($(".event_nav").length){
			$('.event_nav').slick({
			  slidesToShow: 4,
			  slidesToScroll: 1,
			  arrows: false,
			  asNavFor: '.event_for',
			  focusOnSelect: true,
			    responsive: [
			    {
			      breakpoint: 1024,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1,
			        infinite: true,
			      }
			    },
			    {
			      breakpoint: 600,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    },
			    {
			      breakpoint: 480,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    }
			  ]
			});
		}	

	/*==============================================================
		slick  Script Start
	==============================================================
	*/
		if($(".banner-slider").length){
		    $('.banner-slider').slick({
			  slidesToShow: 1,
			  fade:true,
			  loop:true,
			  autoplay:true,
			  speed: 1000,
			  slidesToScroll: 1,
			  arrows: false,
			     responsive: [
			    {
			      breakpoint: 1024,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1,
			        infinite: true,
			      }
			    },
			    {
			      breakpoint: 600,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    },
			    {
			      breakpoint: 480,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    }
			  ]
			});
		}
		if($(".featured-causes-slider").length){
		    $('.featured-causes-slider').slick({
			  slidesToShow: 3,
			  slidesToScroll: 1,
			  arrows: true,
			  dots:false,
			     responsive: [
			    {
			      breakpoint: 1024,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1,
			        infinite: true,
			      }
			    },
			    {
			      breakpoint: 991,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 3
			      }
			    },
			    {
			      breakpoint: 767,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 3
			      }
			    },
			    {
			      breakpoint: 480,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    }
			  ]
			});
		}
		if($(".widget_post_slider").length){
		    $('.widget_post_slider').slick({
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  arrows: true,
			  loop:true,
			  dots:false,
			     responsive: [
			    {
			      breakpoint: 1024,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1,
			        infinite: true,
			      }
			    },
			    {
			      breakpoint: 600,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    },
			    {
			      breakpoint: 480,
			      settings: {
			        slidesToShow: 1,
			        slidesToScroll: 1
			      }
			    }
			  ]
			});
		}
    /*
	==============================================================
		Filterable
	==============================================================
	*/
	    if($('#filterable-item-holder-1').length){
			var filter_container = jQuery('#filterable-item-holder-1');

			filter_container.children().css('position','relative');	
			filter_container.masonry({
				singleMode: true,
				itemSelector: '.filterable-item:not(.hide)',
				animate: true,
				animationOptions:{ duration: 800, queue: false }
			});	
			jQuery(window).resize(function(){
				var temp_width =  filter_container.children().filter(':first').width()+30;
				filter_container.masonry({
					columnWidth: temp_width,
					singleMode: true,
					itemSelector: '.filterable-item:not(.hide)',
					animate: true,
					animationOptions:{ duration: 800, queue: false }
				});		
			});	
			jQuery('ul#filterable-item-filter-1 a').click(function(e){	

				jQuery(this).addClass("active");
				jQuery(this).parents("li").siblings().children("a").removeClass("active");
				e.preventDefault();
				
				var select_filter = jQuery(this).attr('data-value');
				
				if( select_filter == "All" || jQuery(this).parent().index() == 0 ){		
					filter_container.children().each(function(){
						if( jQuery(this).hasClass('hide') ){
							jQuery(this).removeClass('hide');
							jQuery(this).fadeIn();
						}
					});
				}else{
					filter_container.children().not('.' + select_filter).each(function(){
						if( !jQuery(this).hasClass('hide') ){
							jQuery(this).addClass('hide');
							jQuery(this).fadeOut();
						}
					});
					filter_container.children('.' + select_filter).each(function(){
						if( jQuery(this).hasClass('hide') ){
							jQuery(this).removeClass('hide');
							jQuery(this).fadeIn();
						}
					});
				}
				
				filter_container.masonry();	
				
			});
		}
	/*
	==============================================================
		gallery hover  Script Start
	==============================================================
	*/

		$(function() {
		
			$(' .layer > ul > li ').each( function() { $(this).hoverdir(); } );

		});


	$(window).load(function() { // makes sure the whole site is loaded
		$('#status').fadeOut(); // will first fade out the loading animation
		$('#preloader').delay(100).fadeOut('slow'); // will fade out the white DIV that covers the website.
		$('body').delay(500).css({'overflow':'visible'});
	})
	/*
	==============================================================
		gallery hover  Script Start
	==============================================================
	*/
		if($(".lightgallery").length){
			$('.lightgallery').lightGallery();
		}
	
	/* 
	=======================================================================
	  		 	Pretty Photo Script
	======================================================================= */
		if($("a[data-rel^='prettyPhoto']").length){
			$("a[data-rel^='prettyPhoto']").prettyPhoto();
		}
	/*================================================
					countdown start
	=================================================*/
	
		if($('.countdown-timer').length){
	        $('.countdown-timer').downCount({ date: '08/08/2018 12:00:00', offset: +1 });
	  	}
	  
	  /* ==================================================================
					Number Count Up(WayPoints) Script
	  =================================================================	*/		
	
		if($(".DateCountdown").length){
			$(".DateCountdown").TimeCircles();
		}
	
	/*
	  ==============================================================
		   Progress Bar Script Start
	  ============================================================== */  
		 // This button will increment the value
    $('.qtyplus').click(function(e){
        // Stop acting like a button
        e.preventDefault();
        // Get the field name
        fieldName = $(this).attr('field');
        // Get its current value
        var currentVal = parseInt($('input[name='+fieldName+']').val());
        // If is not undefined
        if (!isNaN(currentVal)) {
            // Increment
            $('input[name='+fieldName+']').val(currentVal + 1);
        } else {
            // Otherwise put a 0 there
            $('input[name='+fieldName+']').val(0);
        }
    });
    // This button will decrement the value till 0
    $(".qtyminus").click(function(e) {
        // Stop acting like a button
        e.preventDefault();
        // Get the field name
        fieldName = $(this).attr('field');
        // Get its current value
        var currentVal = parseInt($('input[name='+fieldName+']').val());
        // If it isn't undefined or its greater than 0
        if (!isNaN(currentVal) && currentVal > 0) {
            // Decrement one
            $('input[name='+fieldName+']').val(currentVal - 1);
        } else {
            // Otherwise put a 0 there
            $('input[name='+fieldName+']').val(0);
        }
    });
	

	$(window).scroll( function (e){
		if(sessionStorage["PopupShown"] != 'yes'){ 
			if($(document).scrollTop()>=$(document).height()/5){
				$('#newsletter-popup').modal('show');
				e.preventDefault();
			}else{
				$('#newsletter-popup').modal({ show: false});
				e.preventDefault();
			}			
		}		
	});
	
	if($(".close").length){
		$(".close").click(function (e){
			$('#newsletter-popup').modal({ show: false});
			e.preventDefault();
			sessionStorage["PopupShown"] = 'yes'; //Save in the sessionStorage if the modal has been shown
		});
	}
	
	 $(window).scroll(function () {
		if ($(this).scrollTop() > 50) {
			$('#back-to-top').fadeIn();
		} else {
			$('#back-to-top').fadeOut();
		}
	});
	// scroll body to 0px on click
	$('#back-to-top').click(function () {
		$('#back-to-top').tooltip('hide');
		$('body,html').animate({
			scrollTop: 0
		}, 800);
		return false;
	});
	
	$('#back-to-top').tooltip('show');
	
	
	
});

function closeSPopup(){
	$('#newsletter-popup').modal({ show: false});
}	



/*
=====================================
	Auto search Felid
====================================
*/
$(function(){
    $('#find-them12').autoComplete({
        minChars: 1,
        source: function(term, suggest){
            term = term.toLowerCase();
            var choices = ['ActionScript', 'AppleScript', 'Asp', 'Assembly',
			'BASIC', 'Batch', 'C', 'C++', 'CSS', 'Clojure', 'COBOL', 'ColdFusion',
			'Erlang', 'Fortran', 'Groovy', 'Haskell', 'HTML', 'Java', 'JavaScript',
			'Lisp', 'Perl', 'PHP', 'PowerShell', 'Python', 'Ruby', 'Scala', 'Scheme',
			'SQL', 'TeX', 'XML','Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California',
			'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 
			'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 
			'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 
			'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 
			'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
			'New Jersey', 'New Mexico', 'New York', 'North Carolina', 
			'North Dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 
			'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 
			'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 
			'West Virginia', 'Wisconsin', 'Wyoming','Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California',
			'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 
			'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 
			'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 
			'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 
			'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
			'New Jersey', 'New Mexico', 'New York', 'North Carolina', 
			'North Dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 
			'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 
			'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 
			'West Virginia', 'Wisconsin', 'Wyoming', 'Kode Forest', 'Aladinthemes' ,'Theme Forest','Google'];
            var suggestions = [];
            for (i=0;i<choices.length;i++)
                if (~choices[i].toLowerCase().indexOf(term)) suggestions.push(choices[i]);
            suggest(suggestions);
        }
    });
    
});
