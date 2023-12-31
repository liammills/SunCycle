import { boot } from 'quasar/wrappers';
import VueGoogleMaps from '@fawmi/vue-google-maps';

export default boot(({ app }) => {
    app.use(VueGoogleMaps, {
        load: {
            key: JSON.parse(process.env.VUE_APP_GOOGLE_MAPS_API_KEY),
        },
    });

    app.component('GMapMap', VueGoogleMaps.GMapMap);
    app.component('GMapAutocomplete', VueGoogleMaps.GMapAutocomplete);
});

