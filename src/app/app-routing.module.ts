import { NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from './pages/inicio/inicio.component';
import { LoginComponent } from './pages/login/login.component';
import { MostrarRecetasComponent } from './pages/mostrar-recetas/mostrar-recetas.component';
import { MostrarUnaRecetaComponent } from './pages/mostrar-una-receta/mostrar-una-receta.component';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { RegistroComponent } from './pages/registro/registro.component';
import { SubirRecetaComponent } from './pages/subir-receta/subir-receta.component';
import { EditarPerfilComponent } from './pages/editar-perfil/editar-perfil.component';
import { MostrarRecetasIngredientesComponent } from './pages/mostrar-recetas-ingredientes/mostrar-recetas-ingredientes/mostrar-recetas-ingredientes.component';



const routes: Routes = [
  {path:'',component:InicioComponent},
  {path:'registro',component:RegistroComponent},
  {path:'login',component:LoginComponent},
  {path:'subirReceta',component:SubirRecetaComponent},
  {path:'subirReceta/:idUsuario',component:SubirRecetaComponent},
  {path:'perfil',component:PerfilComponent},
  {path:'mostrarRecetas',component:MostrarRecetasComponent},
  {path:'mostraUnaReceta',component:MostrarUnaRecetaComponent},
  {path: 'mostraUnaReceta/:idRecetas', component: MostrarUnaRecetaComponent },
  { path: 'perfil/:idUsuario', component: PerfilComponent },
  {path: 'editarPerfil', component: EditarPerfilComponent},
  {path: 'mostrarRecetasIngredientes', component: MostrarRecetasIngredientesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
