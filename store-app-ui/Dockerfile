### STAGE 1: Build ###
FROM node:16-alpine AS app-builder
#RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:alpine
#COPY /etc/nginx/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=app-builder  /usr/src/app/dist/store-app-ui /usr/share/nginx/html
# Expose port 80
EXPOSE 80
