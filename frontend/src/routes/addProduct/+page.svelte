<script lang="ts">
	import { Label, Input, Helper, Select, Fileupload, GradientButton } from 'flowbite-svelte';
	import { onDestroy, onMount } from 'svelte';
	import Alert from '../../components/Alert.svelte';
	import { dataService } from '../../services/categories';
	import { dataServicemarkies } from '../../services/markers';
	import { productDataService } from '../../services/products';
    
	let nombre: string = '';
	let precio: number = 0;
	let descripcion: string = '';
	let categorias: any[] = [];
	let fileuploadprops:File
	let markes: any[] = [];
	let message = '';
	let selectedCat = '';
	let selectedMar = '';
    let errorMessage = "";
	
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
		categorias = value;
	});
	const markers = dataServicemarkies.subscribe((value) => {
		markes = value;
	});
	onDestroy(() => {
		subs();
		markers();
		dataService.stopFetching();
		dataServicemarkies.stopFetching();
		productDataService.stopFetching();
	});
	const handleFile = (event:Event)=>{
		const input = event?.target as HTMLInputElement
		if(input.files && input.files.length>0){
			fileuploadprops = input.files[0];
		}
	}
	const handleClick = ()=>{
      validate();
	  const formData = new FormData();
	  formData.append("nombre",nombre)
	  formData.append("precio",precio.toString())
	  formData.append("descripcion",descripcion)
	  formData.append("category_id",selectedCat)
	  formData.append("marker_id",selectedMar)
	  if(fileuploadprops){
		formData.append("image",fileuploadprops)
	  }
	  productDataService.sendData(formData)
	 
	}
</script>

{#if message}
	<Alert name={message} />
{/if}
<div class="w-1/2 shadow-xl p-5 rounded-md mt-4">
	<h1 class="text-center font-mono text-4xl"> Agregar producto</h1>
	<form on:submit|preventDefault={handleClick} >
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
				<Select class="mt-2" items={categorias} bind:value={selectedCat} />
			</Label>
		</div>
		<div>
			<Label color={selectedMar === '' ? 'red' : 'green'}>
				Seleccione una categoria
				<Select class="mt-2" items={markes} bind:value={selectedMar} />
			</Label>
		</div>
		<div class="mb-6">
			<Label class="pb-2" color={!fileuploadprops ? "red":"green"}>Subir imagen</Label>
			<Fileupload  {...fileuploadprops} on:change={handleFile}/>
		</div>
		<div class="mb-6">
			<GradientButton type="submit" color="cyanToBlue">Agregar</GradientButton>
		</div>
	</form>
</div>
