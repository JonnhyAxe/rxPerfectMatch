FROM node:7.9

# prepare a user which runs everything locally! - required in child images!
RUN useradd --user-group --create-home --shell /bin/false app


WORKDIR .

#COPY . /perfectMatch

#RUN npm install -g grunt-cli forever
#RUN npm install
#RUN ng serve

RUN npm install -g angular-cli@1.0.0-beta.28.3 && npm cache clean

CMD ["ng", "serve", "--host", "0.0.0.0"]