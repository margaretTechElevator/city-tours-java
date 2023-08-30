<template>
    <div>
        <button 
            v-on:click="loadMap"
            v-show="!mapVisible"
        >
            Load Map
        </button>

        <input id="search-places" type="text" v-model="query">
        <button type="button" v-on:click="triggerSearch">Search</button>

        <GmapMap
            v-show="mapVisible" 
            v-bind:id="map" 
            v-bind:center="mapCenter" 
            v-bind:zoom="13" 
            map-type-id="roadmap" 
            style="width: 70vw; height: 50vh" 
            v-bind:options="{
                zoomControl: true,
                mapTypeControl: false,
                scaleControl: false,
                streetViewControl: false,
                rotateControl: false,
                fullscreenControl: true,
                disableDefaultUi: false
            }"
        >
            <GmapMarker 
                v-bind:key="index" 
                v-for="(m, index) in markers" 
                v-bind:position="m.position" 
                v-bind:clickable="true"
                v-bind:draggable="true" 
                v-on:click="center = m.position" 
            />
        </GmapMap>
        <!-- This div displays content details -->
        <div id="infowindow-content">
            <span id="place-name" class="title"></span><br />
            <strong>Place ID:</strong> <span id="place-id"></span><br />
            <span id="place-address"></span>
        </div>

    </div>
</template>
    
<script>
import { gmapApi } from 'vue2-google-maps'

export default {
    data() {
        return {
            mapVisible: false,
            mapCenter: { lat: 40.700, lng: -73.986 },
            markers: [],
            query: ""
        }

    },
    computed: {
        google: gmapApi
    },
    methods: {
        setCenter() {
            this.map_center = { lat: 10, lng: 10 }
            console.log('move')
        },
        loadMap() {
            this.mapVisible = true;

            const input = document.getElementById('map')
            const map = this.google.maps.Map(input)
            const searchBox = this.google.maps.places.SearchBox(document.getElementById('search-places'))

            // const infowindow = new this.google.maps.InfoWindow();
            // const infowindowContent = document.getElementById("infowindow-content");

            // infowindow.setContent(infowindowContent)

            //bias search within bounds of current view
            map.addListener(
                'bounds_changed',
                () => {
                    searchBox.setBounds(map.getBounds())
                }
            )

            this.google.maps.event.addListener(
                searchBox,
                'places_changed',
                () => {
                    console.log('method called')
                    
                    const places = searchBox.getPlaces();

                    //don't bother with method if there are no new places
                    if (places.length == 0) {
                        return;
                    }

                    //clear old markers
                    this.markers.forEach((marker) => {
                        marker.setMap(null)
                    })
                    this.markers = [];

                    // For each place, get the icon, place name, and location.
                    const bounds = this.google.maps.LatLngBounds();

                    places.forEach((place) => {
                        if (!place.geometry || !place.geometry.location) {
                            console.log("Returned place contains no geometry");
                            return;
                        }

                        const image = {
                            url: place.icon,
                            size: new this.google.maps.Size(71, 71),
                            origin: new this.google.maps.Point(0, 0),
                            anchor: new this.google.maps.Point(17, 34),
                            scaledSize: new this.google.maps.Size(25, 25)
                        };

                        

                        // Create a marker for each place.
                        let marker = new this.google.maps.Marker({
                            map: map,
                            icon: image,
                            title: place.name,
                            position: place.geometry.location
                        });

                        this.markers.push(marker);

                        bounds.extend(place.geometry.location);
                    })
                    

                    map.fitBounds(bounds);
                }
            )



        },
        triggerSearch() {
            this.google.maps.places.PlacesService.findPlaceFromQuery(
                this.google.maps.places.FindPlaceFromQueryRequest({
                    query: this.query,
                    fields: [
                        'name',
                        'formatted_address',
                        'place_id',
                        'types',
                    ] 
                }),

            )
        }
    },
    mounted() {

    }
}

</script>

<style scoped>
/* #map {
        width: 50vh;
        height: ;
    } */
</style>