<template>
  <body>
    <div>
      <Navigation />
      <div class="mapContainer">
        <google-map />
      </div>
      <div class="itinerary">
        <ItineraryList />
      </div>
      <div class="route">
        <Route />
      </div>

    </div>
  </body>
</template>
  
<script>
import Route from "../components/Route.vue";
import GoogleMap from "../components/GoogleMap.vue";
import Navigation from "../components/Navigation.vue";
import ItineraryList from "../components/ItineraryList.vue";

export default {
  name: "PlanNewTourPage",
  components: {
    Navigation,
    GoogleMap,
    ItineraryList,
    Route,
  },
  computed: {
    landing() {
      return this.$route.path == "/";
    },
  },
};
</script>


<style scoped>

Navigation {
  position: relative;
  z-index: 2;
  border: pink solid 3px;
}
google-map {
  position: relative;
  z-index: 0;

}
#mapContainer {
  margin-top: 110px;
  border: green 3px solid;
}
.route{
  position: relative;
  top: 900px;
  border: red solid 3px;


}
.itinerary{
  display: none;
  position: relative;
  top: 700px;
  height: 200px;
  border: rgb(0, 0, 0) 4px solid;

}
</style>