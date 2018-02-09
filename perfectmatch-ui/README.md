# ArtistApp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.6.0.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).



# Artist app 

>ng new artist-app --routing


#Generating the Components
In this app, a major module is a artis module. It will include artist-list sub component and artist-create sub component.

* artist-list sub component – use for list users.
* artist-create sub component – use for artist creating and editing form.

Use this command to generate use the artist module.
>cd artist-app 

> cd src

>ng generate module artist --routing

Then we need to a service to call the REST services. We called it a artist service.

>ng generate service artist/artist

Now we have to generate sub-components.

>ng generate component /artist/artist-list

>ng generate component /artist/artist-create


#Routing
The Routing artist for redirect to the related page base on the url.
Modify artist-routing.module.ts in the artist module


Then, add the ArtistModule into the app.module.ts.



#Artist class 

> ng generate class artist

> ng generate class artists-mock


http://localhost:4200/artist



#Styling

>npm install --save bootstrap font-awesome

