<template>
  <div>
    <table v-if="failureMessage == ''" id="itineraryList">
      <tr>
        <th class="longInput">trip</th>
        <th class="shortInput">date</th>
        <th class="shortInput">stops</th>
        <th class="longInput">starting location</th>
        <th class="longInput">ending location</th>
        <th class="shortInput">driving time</th>

      </tr>
      <tr
        class="itinerariesList"
        v-for="(itinerary, index) of itineraries"
        v-bind:key="index"
      >
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
import ItineraryService from "../services/ItineraryService";

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
      ItineraryService.getAllItineraries()
        .then((response) => {
          this.itineraries = response;
        })
        .catch((error) => {
          this.failureMessage = `Our apologies! ${error.response}`;
        });
    },
  },

  mounted() {
    this.getAllItineraries();
  },
};
</script>

<style scoped>

.longInput {
  width: 10rem;
}
.shortInput {
  width: 0.5rem;
}

#itineraryList {
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  font-family: Inter;
  font-size: 16px;
  letter-spacing: 1px;
  text-transform: lowercase;
  width: 100%;
  margin-top: 10px;
  background-image: linear-gradient(
    180deg,
    #194f77 97.92%
    #183c55 75.52%,
    #183143 54.69%,
    #182c3a 36.46%,
    #182935 0%,
  );
  border-radius: 20px 20px 0px 0px;
  /* border: 3px pink solid; */
}


#itineraryList th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: transparent;
  color: --menu-bar-mint-50, rgba(207, 231, 202, 0.5);


}
</style>