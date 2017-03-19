// templatemo 467 easy profile

// PRELOADER

$(window).load(function(){
    $('.preloader').delay(100).fadeOut("slow"); // set duration in brackets
});

// HOME BACKGROUND SLIDESHOW
$(function(){
    jQuery(document).ready(function() {
		$('body').backstretch([
	 		 "/images/bg1.jpg",
	 		 "/images/bg2.jpg",
			 "/images/bg3.jpg"
	 			], 	{duration: 3200, fade: 1300});
		});
})