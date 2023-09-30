<template>
<<<<<<< HEAD
  <body id="accountArea">
    <div id="myTripsTitle">my trips</div>
    <trips-table />
    <div id="myToursTableBackground"></div>
=======
  <body>
    <Navigation />
    <div>
      <h4>my tours</h4>
      <button v-on:click="redirectToNewItinerary">+ new itinerary</button>
    </div>
    <ItineraryList />
    <div id="myToursTableBackground"></div>  
>>>>>>> main
  </body>
</template>
  
<script scoped>
<<<<<<< HEAD
import TripsTable from "../components/TripsTable.vue";

export default {
  components: { TripsTable },
  name: "MyToursPage",
};
</script>
  
<style scoped>
body {
  background: url("https://i.ibb.co/TKxNQrR/ny-Opacity.png");
}
=======
  import Navigation from '../components/Navigation.vue'
  import ItineraryList from '../components/ItineraryList.vue'

  export default {
    components: { Navigation, ItineraryList },
    name: 'MyToursPage',
    methods: {
      redirectToNewItinerary() {
        this.$router.push("PlanNewTourPage");
      },
    },
  }
</script>
  
<style scoped>
  body {
    background: url("https://i.ibb.co/TKxNQrR/ny-Opacity.png");
  }
>>>>>>> main

#accountArea {
  width: 100%;
  height: 700px;
  border-radius: 0px 0px 20px 20px;
  background: linear-gradient(
    180deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
}
#myTripsTitle {
  color: var(--selected-green, #adff00);
  text-align: center;
  font-family: Orelega One;
  font-size: 40px;
  font-style: normal;
  font-weight: 400;
  line-height: 50px; /* 125% */
  letter-spacing: 1px;
  text-transform: lowercase;
}
</style>