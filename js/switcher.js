  jQuery(document).ready(function($) {
	
	var typewidthval = jQuery.cookie("ts_cookie_typewidthval");
	if(typewidthval=="fullwidth-layout"){
		var fixedselected = "";
		var fullwidthselected = 'selected="selected"';
	}else{
		var fixedselected = 'selected="selected"';
		var fullwidthselected = '';
	}
	
	var styleswitcherstr = ' \
	<div id="style-switcher"> \
	<form id="style-switcher-form">\
	  <div class="switchercontainer"> \
		  <span>Type Width</span> \
		  <div id="typewidthcontainer"> \
		  	<select id="typewidth" name="typewidth"> \
				<option value="fixed-layout" '+fixedselected+'>Fixed Width</option> \
				<option value="fullwidth-layout" '+fullwidthselected+'>Full Width</option> \
			</select> \
		  </div> \
		  <div class="clear"></div> \
	  </div> \
	  <div class="switchercontainer"> \
		  <a href="#" id="switcher-reset">Reset</a> \
		  <div class="clear"></div> \
	  </div> \
	</form>\
	</div> \
	';
	
	jQuery("body").prepend( styleswitcherstr );
	
	jQuery('#typewidth').change(function(e){
		var typewidthval = jQuery(this).val();
		jQuery("#layoutcss").attr("href","styles/"+typewidthval+".css");
		
		
		jQuery.cookie("ts_cookie_typewidthval", typewidthval);
	});
    
	
  var typewidthval		= jQuery.cookie("ts_cookie_typewidthval");
  
  
  if (typewidthval) {
      jQuery("#layoutcss").attr("href","styles/"+typewidthval+".css");
  } 
  
  
  jQuery("#switcher-reset").click(function(){
		
		var typewidthval = "fixed-layout";
		jQuery("#layoutcss").attr("href","styles/"+typewidthval+".css");
		jQuery.cookie("ts_cookie_typewidthval",typewidthval);
		
		jQuery("#typewidth").val("fixed-layout");
		 
  });
         
});