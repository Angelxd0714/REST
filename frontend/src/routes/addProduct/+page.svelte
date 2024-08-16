<script lang="ts">
	import { Label, Input, Helper, Select, Fileupload, GradientButton } from 'flowbite-svelte';
	import { onDestroy, onMount } from 'svelte';
	import Alert from '../../components/Alert.svelte';
	import { dataService } from '../../services/categories';
	import { dataServicemarkies } from '../../services/markers';
	import { productDataService } from '../../services/products';
	import type { marker } from '../../models/makerInterface';
	import type { categoInterface } from '../../models/categoriaInterface';

	let nombre: string = '';
	let precio: number = 0;
	let descripcion: string = '';
	let categorias: any[] = [];
	let fileuploadprops: File;
	let markes: any[] = [];
	let message = '';
	let selectedCat: any;
	let selectedMar: any;
	const validate = () => {
		if (nombre.trim() === '' || precio === 0 || descripcion.trim() === '') {
			message = 'campo es requerido';
		} else {
			message = '';
		}
	};
	onMount(async () => {
		dataService.startFetching();
		dataServicemarkies.startFetching();
	});

	const subs = dataService.subscribe((value) => {
		categorias = markes = value.map((valores: categoInterface) => ({
			value: valores.id?.toString(), // Asegúrate de que `value` sea una cadena
			name: valores.name
		}));
	});
	const markers = dataServicemarkies.subscribe((values: any) => {
		markes = values.map((valores: marker) => ({
			value: valores.id?.toString(), // Asegúrate de que `value` sea una cadena
			name: valores.name
		}));
	});
	onDestroy(() => {
		subs();
		markers();
		
	});
	const handleFile = (event: Event) => {
		const input = event?.target as HTMLInputElement;
		if (input.files && input.files.length > 0) {
			fileuploadprops = input.files[0];
		}
	};
	const handleSelectChange = () => {
		console.log('Categoría seleccionada:', selectedCat);
		console.log('Marcador seleccionado:', selectedMar);
	};
	const handleClick = () => {
		validate();
		const formData = new FormData();
		formData.append("nombre", nombre);
		formData.append('precio', precio.toString());
		formData.append('descripcion', descripcion);
		formData.append('category_id', selectedCat);
		formData.append('marker_id', selectedMar);
		if (fileuploadprops) {
			formData.append('imagen', fileuploadprops);
		}
		productDataService.sendData(formData);
		for (let [key, value] of formData.entries()) {
        console.log(`${key}: ${value}`);
    }
	};
</script>

{#if message}
	<Alert name={message} />
{/if}
<div class="mt-4 w-1/2 rounded-md p-5 shadow-xl">
	<h1 class="text-center font-mono text-4xl">Agregar producto</h1>
	<form on:submit|preventDefault={handleClick} enctype="multipart/form-data">
		<div class="mb-6">
			<Label for="nombre" color={nombre.trim() == '' ? 'red' : 'green'} class="mb-2 block"
				>Nombre Producto</Label
			>
			<Input
				id="nombre"
				color={nombre.trim() == '' ? 'red' : 'green'}
				placeholder="Escribe nombre"
				bind:value={nombre}
			/>
			{#if message}
				<Helper class="mt-2" color="red"><span class="font-medium"></span> {message}</Helper>
			{/if}
		</div>
		<div class="mb-6">
			<Label for="precio" color={precio == 0 ? 'red' : 'green'} class="mb-2 block">Precio</Label>
			<Input
				type="number"
				id="precio"
				color={precio == 0 ? 'red' : 'green'}
				placeholder="Ingrese precio $"
				bind:value={precio}
			/>
			{#if message}
				<Helper class="mt-2" color="red"><span class="font-medium"></span> {message}</Helper>
			{/if}
		</div>
		<div class="mb-6">
			<Label
				for="descripcion"
				color={descripcion.trim() === '' ? 'red' : 'green'}
				class="mb-2 block">Descripcion</Label
			>
			<Input
				id="descripcion"
				color={descripcion.trim() === '' ? 'red' : 'green'}
				placeholder="Ingrese descripcion"
				bind:value={descripcion}
			/>
			{#if message}
				<Helper class="mt-2" color="red"><span class="font-medium"></span> {message}</Helper>
			{/if}
		</div>
		<div>
			<Label color={selectedCat === '' ? 'red' : 'green'}>
				Seleccione una categoria
				<Select
					class="mt-2"
					items={categorias}
					on:change={handleSelectChange}
					bind:value={selectedCat}
				/>
			</Label>
		</div>
		<div>
			<Label color={selectedMar === '' ? 'red' : 'green'}>
				Seleccione una fabricante
				<Select
					class="mt-2"
					items={markes}
					on:change={handleSelectChange}
					bind:value={selectedMar}
				/>
			</Label>
		</div>
		<div class="mb-6">
			<Label class="pb-2" color={!fileuploadprops ? 'red' : 'green'}>Subir imagen</Label>
			<Fileupload {...fileuploadprops} on:change={handleFile} />
		</div>
		<div class="mb-6">
			<GradientButton type="submit" color="cyanToBlue">Agregar</GradientButton>
		</div>
	</form>
</div>
