<template>
  <body>
    <div v-if="landing" id="container">
      <topper/>
      <SearchBarFilterGo  id="search"/>
      <google-map id="map" />
      <Route class="route" />

    </div>
   
  </body>
</template>
  
<script>
import Route from "../components/Route.vue";
import GoogleMap from "../components/GoogleMap.vue";
import SearchBarFilterGo from "../components/searchBarFilterGo.vue";100
import Topper from '../components/Topper.vue';

export default {
  name: "PlanNewTourPage",
  components: {
    GoogleMap,
    Route,
    SearchBarFilterGo,

    Topper
    
  },
  computed: {
    landing() {
      return this.$route.path == "/";
    },
  },
};
</script>
<style scoped>

#map{
  width:100%;
  height:90%;
  z-index: 1;
  position:absolute;
}
#route{
  z-index: 2;

}
#search{
  z-index: 3;

}


</style>