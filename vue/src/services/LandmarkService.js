import axios from 'axios';

export default {
    addLandmark(landmark) {
        return axios.post(`/landmarks`, landmark)
            .then(response => {
                if (response.status === 201) {
                    return
                }
            })
            .catch(error => {
                return error
            })

    },
    getLandmarkByPlaceId(placeId) {
        return axios.get(`/itineraries/${placeId}`)
            .then(response => {

                if (response.status === 200) {
                    return response.data
                }
            })
            .catch(error => {
                return error
            })
    },
    getLandmarkByCity(city) {
        return axios.get(`/itineraries?city=${city}`)
            .then(response => {

                if (response.status === 200) {
                    return response.data
                }
            })
            .catch(error => {
                return error
            })
    },
    getLandmarkByCity(type) {
        return axios.get(`/itineraries?city=${type}`)
            .then(response => {

                if (response.status === 200) {
                    return response.data
                }
            })
            .catch(error => {
                return error
            })
    },

}