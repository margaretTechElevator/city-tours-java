<template>
    <div>
        <!-- <h1>{{ name }}</h1> -->
        <div>
            <img id="locPhoto" v-bind:src="photos[0].getUrl()">
            <!-- <aside v-for="attribution in photos[0].html_attributions" v-bind:key="attribution.id">
                Photo Credit: <span v-html="attribution"></span><br>
            </aside> -->
        </div>
        <section class="contact-details">
            <p id="address">{{ address }}</p>
            <p id="phone" v-show="phoneNumber">{{ phoneNumber }}</p>
            <a id="website" v-show="website" v-bind:href="website">Visit the Website</a>
        </section>
    </div>
</template>

<script>


export default {
    name: 'LandmarkInfo',
    props: {
        name: String,
        placeId: String,
        address: String,
        reviews: Object,
        photos: {
            type: Array,
            default: () => [{
                getUrl: () => ''
            }]
        },
        phoneNumber: String,
        ratings: Object,
        website: String
    },
    methods: {
        defaultUrl() {
            return [
                {
                    getUrl: () => ''
                }
            ]
        }
    }
}

</script>

<style scoped>
h1 {
    color: #ADFF00;
    font-family: OrelegaOne;
  font-size: 24px;
  color: rgba(207, 231, 202);
  background-color: transparent;
  text-decoration: none;
  /* resize: none; */
  text-transform: lowercase;
  text-align: center;
  border: none;
  width: 100%;
}

#locPhoto {
    border-radius: 50%;
    height: 120px;
    max-width: 120px;
}
</style>