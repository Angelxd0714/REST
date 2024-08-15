<script lang="ts">
    import { Alert } from 'flowbite-svelte';
    import { InfoCircleSolid } from 'flowbite-svelte-icons';
    export let name = ""
    export let type:any = ""
    export let message = ""
  </script>
  <Alert color={type}>
    <InfoCircleSolid slot="icon" class="w-5 h-5" />
    <span class="font-medium">{message}</span>
   {name}
  </Alert>