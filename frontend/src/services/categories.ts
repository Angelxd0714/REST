import { writable } from 'svelte/store';

const getAllcategries = () => {
    const { subscribe, set, update } = writable([]);

    let intervalId:any;
    
    const fetchData = async () => {
        try {
            const response = await fetch("http://172.27.0.3:8060/api/v1/category/allCategories");
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            set(data); // Actualiza el store con los datos obtenidos
        } catch (error) {
            console.error('Fetch error:', error);
        }
    };

    return {
        subscribe,
        startFetching: () => {
            fetchData();
            // Aquí puedes iniciar intervalos o configurar la suscripción
        },
        stopFetching: () => {
            clearInterval(intervalId); // Limpia intervalos o cancela solicitudes
        }
    };
};

export const dataService = getAllcategries();