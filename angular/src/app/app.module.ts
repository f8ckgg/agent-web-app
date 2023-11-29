import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {JwtInterceptor} from "./JwtInterceptor";
import {RouterModule, Routes} from "@angular/router";

import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";
import {MatCardModule} from "@angular/material/card";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatNativeDateModule} from "@angular/material/core";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatSelectModule} from "@angular/material/select";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatMenuModule} from "@angular/material/menu";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatDialogModule} from "@angular/material/dialog";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {UserComponent} from "./components/user/user.component";
import { AgentsComponent } from './components/agents/agents.component';
import { AgentComponent } from './components/agent/agent.component';
import { AdminPropertiesComponent } from './components/admin-properties/admin-properties.component';
import { AdminPropertyComponent } from './components/admin-property/admin-property.component';
import { UserPropertiesComponent } from './components/user-properties/user-properties.component';
import { AdminDealComponent } from './components/admin-deal/admin-deal.component';
import { UserDealComponent } from './components/user-deal/user-deal.component';
import {CreateRatingComponent} from "./components/create-rating/create-rating.component";

const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'user',component:UserComponent},
  {path:'agents',component:AgentsComponent},
  {path:'agent',component:AgentComponent},
  {path:'agent/:id',component:AgentComponent},
  {path:'adminproperties',component:AdminPropertiesComponent},
  {path:'adminproperty',component:AdminPropertyComponent},
  {path:'adminproperty/:id',component:AdminPropertyComponent},
  {path:'userproperties',component:UserPropertiesComponent},
  {path:'admindeal',component:AdminDealComponent},
  {path:'userdeals',component:UserDealComponent},
  {path:'createrating/:id', component:CreateRatingComponent}
]
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent,
    AgentsComponent,
    AgentComponent,
    AdminPropertiesComponent,
    AdminPropertyComponent,
    UserPropertiesComponent,
    AdminDealComponent,
    UserDealComponent,
    CreateRatingComponent
  ],
  imports: [
    MatSnackBarModule,
    RouterModule.forRoot(routes),
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    MatDatepickerModule,
    MatCheckboxModule,
    MatNativeDateModule,
    MatProgressBarModule,
    MatSelectModule,
    MatTableModule,
    MatPaginatorModule,
    MatToolbarModule, MatMenuModule,
    MatDialogModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
