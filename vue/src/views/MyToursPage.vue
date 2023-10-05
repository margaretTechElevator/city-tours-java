<template>
  <div id="myToursPage">
    <topper />

    <div id="accountArea">
      <div id="myTripNewTrip">
        <div id="myTripsTitle">my trips</div>
        <button v-on:click="redirectToNewItinerary" id="newTrip">+ new trip</button>
      </div>
      <ItineraryList id="tripList"/>
    </div>
  </div>
</template>
  
<script scoped>
import ItineraryList from "../components/ItineraryList.vue";
import Topper from "../components/Topper.vue";

export default {
  components: { ItineraryList, Topper },
  name: "MyToursPage",

  methods: {
    redirectToNewItinerary() {
      this.$router.push("PlanNewTourPage");
    },
  },
};
</script>
  
<style scoped>

#myTripNewTrip{
  display: flex;
  justify-content: space-between;
  margin-left: 26px;
  margin-right: 26px;
  
}
body {
  background: url("https://i.ibb.co/TKxNQrR/ny-Opacity.png");
}

#accountArea {
  padding-top: 100px;
  width: 100%;
  height: 100%;
  background: 
    #182935 100%,
}
#newTrip{
  margin-top: 30px;
}
#myTripsTitle {
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));

  font-family: Orelega One;
  font-size: 40px;
  font-style: normal;
  font-weight: 400;
  line-height: 50px; /* 125% */
  letter-spacing: 1px;
  text-transform: lowercase;
  margin-top: 30px;
  padding-left: 26px;
}
button{
  background-color: transparent;
  border-style: none;
  font-family: Orelaga One;
  font-size: 32px;
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  font-weight: 900;
}
#tripList{
  padding-left: 26px;
  padding-right: 26px;
}
</style>