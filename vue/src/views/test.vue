<template>
  <body>
    <div class="testContainer">

      <!-- navigation -->
      <div id="nav">
        <navigation />
      </div>

      <!-- plan your next adventure dropdown filter area-->
      <div id="dropdown">
        <plan-trip-dropdown-filter id="planDropdown"/>
      </div>

      <!-- map -->
        <div id="map">
          <div>INTERACTIVE MAP + LOCATION DETAILS  (Make the basic details viewable on-hover of landmark pin.  Make all (more) details be a pop up of the location card on click of basic popup "details" button (or if it makes more sense, on-click of anywhere on the basic details pop up) .  Add an "add to trip" button on the on-hover basic details location card as well as the detailed on-click details.   Make the login page pop up when you click on the button "add to trip" to your trip if you arent already logged in)  if I can, make the popups float over existing map instead of displaying details to the right to maintain balance in design</div>
          <!-- <google-map/> -->
        </div>

        <!-- route -->
        <div id="route">
          <div>MY TRIP AREA (make expandable to fit the remaining area below) Inside this area should be information from each locations card (limited) as well as the route, time traveled, and distance if possible.  I should also have a "save trip" button</div>
        </div>

    </div>
  </body>
</template>

<script>
import Navigation from "../components/Navigation.vue";
import PlanTripDropdownFilter from "../components/planTripDropdownFilter.vue";

export default {
  components: {Navigation, PlanTripDropdownFilter},
  name: "testFlex",
};
</script>

<style scoped>
#planDropdown{

  grid-area: dropdown;
  background-color:aqua;
  border:white 5px solid;

}
#dropdown{
  z-index: 3;
  width: 500px;
  margin: auto;

}
body {
  background-image: url("https://images.pexels.com/photos/9437305/pexels-photo-9437305.jpeg");
  background-size: auto;
}
.testContainer {
  height: 97vh;
  border: rgb(49, 56, 242) solid 6px;
  display: grid;
  grid-template-areas:
    "nav"
    "dropdown"
    "flexBoxWithMapAndRoute"
    "route ";
  grid-template-rows: 100px 30px 360px 1fr;
}

#title {
  border: magenta 3px solid;
  font-weight: 900;
  font-size: 20px;
  padding: 0;
  color: rgb(1, 76, 99);
  text-align: center;
  font-family: Rozha-One;
}
#route {
  background-color: yellow;
  text-align: center;
}
#map {
  z-index: 2;
  background-color: palegoldenrod;
  /* this pushes the map div down and in */
  margin-top:100px;
  margin-left: 20px;
  margin-right: 20px;
  margin-bottom: 20px;

}


#nav {
  grid-area: nav;
  height: 80px;
  z-index: 4;
}
#route{
  background-color:rgb(9, 250, 190);
  margin-left: 20px;
  margin-right: 20px;
  margin-bottom: 20px;

}
</style>