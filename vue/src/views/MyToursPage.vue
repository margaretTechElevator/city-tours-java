<template>
  <div id="myToursPage">
    <Navigation />
    <div id="myToursContainer">
      <!-- individual tour card -->
      <div id="tourCard">
        <div id="tripTitle">New Orleans for Mardi Gras with the girls</div>
        <div id="timeAndRoute">
          <route-icon></route-icon>
          <div id="travelTime">1 hr 35 min</div>
        </div>
        <div id="locationsContainer">
          location photos with hover/click to location popup
          <span> <img id="locationPhoto" src="../assets/person.jpg"/></span>
        </div>
        <div id="route">route / map</div>
      </div>
    </div>
    <ItineraryList />
    <div id="myToursTableBackground"></div>
  </div>
</template>
  
<script scoped>
import Navigation from "../components/Navigation.vue";
import ItineraryList from "../components/ItineraryList.vue";
import routeIcon from "../assets/routeIcon.vue";

export default {
  components: { Navigation, ItineraryList, routeIcon },
  // ,
  //     RouteIconname: 'MyToursPage',
  // methods: {
  //   redirectToNewItinerary() {
  //     this.$router.push("PlanNewTourPage");
  //   },
  // },
};
</script>
  
<style scoped>
#myToursPage {
  background: linear-gradient(
    0deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
  height: 100vh;
}
#myToursContainer {
  margin-top: 30px;
  display: flex;
  justify-content: center;
}
#tourCard {
  border-radius: 20px;
  background-color: #182935;
  width: 90%;
  height: 200px;
}
#tripTitle {
  text-align: center;
  color: rgba(207, 231, 202);
  font-family: Inter;
  font-size: 14px;
  letter-spacing: 1.2px;
  background-color: transparent;
  text-decoration: none;
  text-transform: lowercase;
  border: none;
  overflow-wrap: break-word;
  position: relative;
}
#travelTime {
  text-align: center;
  font-family: Inter;
  font-size: 10px;
  color: rgba(207, 231, 202);
  opacity: 50%;
}
#locationsContainer {
}
#locationPhoto{
  border-radius: 50%;
  width: 60px;
  height: 60px;
  overflow: hidden;
}
</style>