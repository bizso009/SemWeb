
function init(){
    var centerLatLng = new google.maps.LatLng(53.417,-1.444);
    var myOptions = {
        zoom: 4,
        center: centerLatLng,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
    }
    var map = new google.maps.Map(document.getElementById("map"), myOptions);
    
    var coords = document.getElementById("locations").getElementsByTagName("li");
    for (var i = 0; i < coords.length; i++) {
        var lat = coords[i].getElementsByTagName("span")[0].innerHTML;
        var lng = coords[i].getElementsByTagName("span")[1].innerHTML;
        var myLatlng = new google.maps.LatLng(lat, lng);
        var marker = new google.maps.Marker({
            position: myLatlng
        });
        marker.setMap(map);
    };
    
    
    // To add the marker to the map, call setMap();
}
