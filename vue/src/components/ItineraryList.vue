<template>
  <div>
    <table v-if="failureMessage == '' && this.isLoggedIn && this.itineraries.length !== 0" id="itineraryList">
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
    <p v-else-if="!this.isLoggedIn">Once you log in, you can see your existing itineraries here!</p>
    <p v-else-if="failureMessage != ''">{{ failureMessage }}</p>
    <p v-else>You haven't saved any itineraries yet.</p>
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
    computed: {
      isLoggedIn() {
        return this.$store.state.token !== "";
      }
    },
    methods: {
      getAllItineraries() {
        if (this.isLoggedIn) {
          ItineraryService.getAllItineraries().then((response) => {
            this.itineraries =response;
          })
          .catch((error) => {
            this.failureMessage = `Our apologies! ${error.response}`;
          })
        }
      },
    },

    mounted() {
      this.getAllItineraries();
    },
  };
</script>

<style>
  #itineraryList {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    border-collapse: collapse;
    width: 100%;
    margin-top:10px;
    background-color: #fffefe;
  }

  #itineraryList td, #itineraryList th {
    padding: 4px;
    text-align: center;
  }

  #itineraryList tr:nth-child(even) {
    background-color: #f2f2f2;
  }

  #itineraryList tr:hover {
    background-color: #ddd;
  }
  
  #itineraryList th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #ffab3d;
    color: white;
    text-align: center;
    text-shadow: 2px 2px 5px rgba(15, 12, 6, 0.164);
  }
</style>