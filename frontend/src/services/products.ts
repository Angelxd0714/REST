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
    const sendData = async (data:any={})=>{
       
        try {
            const add = await fetch("http://172.27.0.3:8060/api/v1/product/add",{
                method:"POST",
                body:data
            })
            if(!add.ok){
                throw new Error('Network response was not ok');
            }
            const response = await add.json();
            message = response.message;
        } catch (error) {
            console.error('Fetch error:', error);
            
        }
    }
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