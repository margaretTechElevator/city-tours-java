<template>
  <div id="itinerary">
    <table v-if="failureMessage == ''" id="itineraryList">
      <tr>
        <th>city</th>
        <th>date</th>
        <th>starting location</th>
        <th>ending location</th>
        <th>number of stops</th>
      </tr>
      <tr class="itinerariesList" v-for="(itinerary, index) of itineraries" v-bind:key="index">
        <td>{{ itinerary["startLocation"] }}</td>
        <td>content</td>
        <td>content</td>
        <td>content</td>
        <td>{{ itinerary["landmarks"].length }}</td>
      </tr>
    </table>
    <p v-else>{{ failureMessage }}</p>
  </div>
</template>

<script>
import ItineraryService from '../services/ItineraryService'

/** each itinerary object has these properties:
 *     city: "",
 *     date: "",
 *     starting_location: "",
 *     ending_location: "",
 *     landmarks: []
 */
  export default {
    name: "ItineraryList",
    data() {
      return {
        failureMessage: "",
        itineraries: [],
      };
    },
    methods: {
      getAllItineraries() {
        ItineraryService.getAllItineraries().then((response) => {
          this.itineraries =response;
        })
        .catch((error) => {
          this.failureMessage = `Our apologies! ${error.response}`;
        })
      },
    },

    mounted() {
      this.getAllItineraries();
    },
  };
</script>

<style scoped>
/* #itinerary{
  background: linear-gradient(
    0deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
  border-radius: 20px 20px 0 0 ;
  box-shadow: 5px -1px 5px rgba(5, 5, 0, 0.4);

} */
 
</style>