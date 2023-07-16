import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BarRatingModule} from 'ngx-bar-rating';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './sharepage/navbar/navbar.component';
import { FooterComponent } from './sharepage/footer/footer.component';
import { InicioComponent } from './pages/inicio/inicio.component';
import { RegistroComponent } from './pages/registro/registro.component';
import { LoginComponent } from './pages/login/login.component';
import { SubirRecetaComponent } from './pages/subir-receta/subir-receta.component';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { MostrarRecetasComponent } from './pages/mostrar-recetas/mostrar-recetas.component';
import { MostrarUnaRecetaComponent } from './pages/mostrar-una-receta/mostrar-una-receta.component';
import { recetasService } from './pages/mostrar-recetas/recetasService.service';
import { EditarPerfilComponent } from './pages/editar-perfil/editar-perfil.component';
import { CapturaPasoComponent } from './pages/subir-receta/captura-paso.component';
import { MostrarRecetasIngredientesComponent } from './pages/mostrar-recetas-ingredientes/mostrar-recetas-ingredientes/mostrar-recetas-ingredientes.component';
import { FechaPipe } from './fecha.pipe';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    InicioComponent,
    RegistroComponent,
    LoginComponent,
    SubirRecetaComponent,
    PerfilComponent,
    MostrarRecetasComponent,
    MostrarUnaRecetaComponent,
    EditarPerfilComponent,
    CapturaPasoComponent,
    MostrarRecetasIngredientesComponent,
    FechaPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BarRatingModule,
    HttpClientModule
    
  ],
  providers: [recetasService],
  bootstrap: [AppComponent]
})
export class AppModule { }
