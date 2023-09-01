import axios from 'axios';
const baseURL = 'localhost:9000'

export default {

    /**
     * 
     * @param {{}} itinerary 
     * @returns void action is successful or an error object if not
     */
    addItinerary(itinerary) {
        return axios.post(`${baseURL}/itineraries`, itinerary)
            .then(response => {
                if (response.status === 201) {
                    return
                }
            })
            .catch(error => {
                return error
            })

    },

    /**
     * 
     * @param {{}} itinerary 
     * @returns void if action is successful or an error object if not
     */
    updateItinerary(itinerary) {
        return axios.put(`${baseURL}/itineraries`, itinerary)
            .then(response => {
                if (response.status === 201) {
                    return
                }
            })
            .catch(error => {
                return error
            })

    },

    /**
     * 
     * @returns {} itinerary object on success or error
     */
    getNextItinerary() {
        return axios.get(`${baseURL}/itineraries`)
            .then(response => {

                if (response.status === 200) {
                    return response.data
                }
            })
            .catch(error => {
                return error
            })
    },

    getAllItineraries() {
        return axios.get(`${baseURL}/itineraries/all`)
        .then(response => {

            if (response.status === 200) {
                return response.data
            }
        })
        .catch(error => {
            return error
        })
    },

    /**
     * 
     * @param {Date} date 
     * @returns {} itinerary object or error
     */
    getItineraryByDate(date) {
        //ensure proper date format
        let year = new Intl.DateTimeFormat('en', { year: 'numeric' }).format(date);
        let month = new Intl.DateTimeFormat('en', { month: '2-digit' }).format(date);
        let day = new Intl.DateTimeFormat('en', { day: '2-digit' }).format(date);
        date = `${month}${day}${year}`

        return axios.get(`${baseURL}/itineraries/${date}`)
        .then(response => {

            if (response.status === 200) {
                return response.data
            }
        })
        .catch(error => {
            return error
        })
    }


}



