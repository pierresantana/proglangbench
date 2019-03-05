const ProductsRoute = require('./products.route');

module.exports = (app) => {
    ProductsRoute.routesConfig(app);
}