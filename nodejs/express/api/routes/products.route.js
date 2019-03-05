const ProductsController = require('../controllers/products.controller');

exports.routesConfig = function (app) {
    app.post('/products', [
        ProductsController.insert
    ]);
    app.get('/products', [
        ProductsController.list
    ]);
    app.get('/products/:productId', [
        ProductsController.getById
    ]);
    app.patch('/products/:productId', [
        ProductsController.patchById
    ]);
    app.delete('/products/:productId', [
        ProductsController.removeById
    ]);
};