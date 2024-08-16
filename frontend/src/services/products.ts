import { writable } from 'svelte/store';

const products = () => {
    const { subscribe, set, update } = writable([]);

    let intervalId:any;
    let message:string;
    const fetchData = async () => {
        try {
            const response = await fetch("http://172.27.0.3:8060/api/v1/product/all");
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            set(data); // Actualiza el store con los datos obtenidos
        } catch (error) {
            console.error('Fetch error:', error);
        }
    };
    const sendData = async (data: any = {}) => {
        try {
            const response = await fetch("http://172.27.0.3:8060/api/v1/product/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data) // Convertir el objeto a JSON
            });
    
            if (!response.ok) {
                const errorResponse = await response.json();
                throw new Error(`Network response was not ok: ${errorResponse.message}`);
            }
    
            const result = await response.json();
            message = result.message;
        } catch (error) {
            console.error('Fetch error:', error);
            message = 'Error occurred while sending data'; // Puedes ajustar el mensaje de error
        }
    };
    return {
        subscribe,
        sendData,
       
        startFetching: () => {
            fetchData();
            message
            intervalId = setInterval(fetchData, 3000);
    
            // Aquí puedes iniciar intervalos o configurar la suscripción
        },
        stopFetching: () => {
            clearInterval(intervalId); // Limpia intervalos o cancela solicitudes
        }
    };
};

export const productDataService = products();