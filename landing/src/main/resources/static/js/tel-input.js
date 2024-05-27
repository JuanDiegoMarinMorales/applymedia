window.onload = function() {
    document.getElementById('msisdn').value = '';
}

$(document).ready(function() {
    $(":input").inputmask();
    
    $("#msisdn").inputmask({ mask: "999 999 9999" });

    $( "#msisdn" ).on( "keypress", function() {
    var inputValue = $("#msisdn").val();
    var sanitizedValue = inputValue.replace(/\s/g, ''); // Remove spaces
    var modifiedValue = "964" + sanitizedValue; // Add "27" before the sanitized value

    $('#msisdnOriginal').val(modifiedValue);
} );
});