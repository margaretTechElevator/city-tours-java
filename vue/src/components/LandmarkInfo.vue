<template>
    <div>
        <h1>{{ name }}</h1>
        <div>
            <img v-bind:src="photos[0].getUrl()">
            <aside v-for="attribution in photos[0].html_attributions" v-bind:key="attribution.id">
                Photo Credit: <span v-html="attribution"></span><br>
            </aside>
        </div>
        <section class="contact-details">
            <p>{{ address }}</p>
            <p v-show="phoneNumber">{{ phoneNumber }}</p>
            <a v-show="website" v-bind:href="website">Visit the Website</a>
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
    font-size: medium;
    color: brown;
}

img {
    max-width: 50%;
}
</style>