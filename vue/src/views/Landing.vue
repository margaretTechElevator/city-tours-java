<template>
  <body>
    <div>
      <Navigation />
      <div id="tagline">
        <div>your city,</div>
        <div>your adventure,</div>
        <div>perfectly planned.</div>
      </div>
    </div>
    <Map id="map"/>
  </body>
</template>
  
<script>
import Map from "../components/GoogleMap.vue";
import Navigation from "../components/Navigation.vue";

export default {
  name: "Landing",
  components: { Navigation, Map },
};
</script>
  
<style scoped>
body {
  background-color: darkgreen;
  /* background-image: url("https://i.ibb.co/TKxNQrR/ny-Opacity.png"); */
}


#map{
  padding-top:500px;
  margin-left: 30px;
  margin-right: 30px;
  margin-top:20px;
  border: magenta 5px solid;
}

#tagline {
  text-align: center;
  font-size: 1.5rem;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  font-weight: 900;
  color: rgb(150, 255, 4);
}
</style>