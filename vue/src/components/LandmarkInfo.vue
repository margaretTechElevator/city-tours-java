<template>
  <div class="locationInfo">
    <div id="name">{{ name }}</div>
    <div id="photoAndInfo">
      <img id="locPhoto" v-bind:src="photos[0].getUrl()" />
      <!-- <aside v-for="attribution in photos[0].html_attributions" v-bind:key="attribution.id">
                Photo Credit: <span v-html="attribution"></span><br>
            </aside> -->

      <section class="contact-details">
        <p id="address">{{ address }}</p>
        <p id="phone" v-show="phoneNumber">{{ phoneNumber }}</p>
        <a id="website" v-show="website" v-bind:href="website"
          >Visit the Website</a
        >
      </section>
    </div>
  </div>
</template>

<script>
export default {
  name: "LandmarkInfo",
  props: {
    name: String,
    placeId: String,
    address: String,
    reviews: Object,
    photos: {
      type: Array,
      default: () => [
        {
          getUrl: () => "",
        },
      ],
    },
    phoneNumber: String,
    ratings: Object,
    website: String,
  },
  methods: {
    defaultUrl() {
      return [
        {
          getUrl: () => "",
        },
      ];
    },
  },
};
</script>

<style scoped>
#photoAndInfo {
  width: 100%;
  position: relative;
  top: 0px;
  
  display: flex;
  justify-content: space-between;
}
#name {
  color: #adff00;
  font-family: OrelegaOne;
  font-size: 24px;
  background-color: transparent;
  text-decoration: none;
  text-transform: lowercase;
  text-align: center;
  border: none;
  width:100%;
  min-width: 280px;
  max-width: 480px;
  position: relative;
  left: 0;
  top: -36px;
  text-align: left;
 
}


#locPhoto {
  border-radius: 50%;
  height: 70px;
  min-height: 70px;
  min-width: 70px;
  width: 70px;
  left: 0px;
}
.locationInfo {
width:  80%;
max-width: 500px;
min-width: 200px;

}
.contact-details {
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  font-family: Inter;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px; /* 166.667% */
  letter-spacing: 1.8px;
  text-decoration: none;
  text-align: right;
  width: 90%;
  min-width: 200px;

}
</style>