#Importar bilbiotecas necesarias
import numpy as np
import matplotlib.pyplot as plt

#Crear una clase perceptron
class Perceptron:

    def __init__(self, num_inputs, lr, epochs, pesos=None):
        if pesos:
            self.weights = pesos  # en caso de contar con pesos del perceptron cargarlos
        else:
            self.weights = np.random.rand(num_inputs + 1)  # el peso extra es el bias

        self.lr = lr  # learning rate
        self.epochs = epochs  # num de iteraciones

#Funciones de activacion disponibles para el perceptron
    def act_fn(self, x, funcion='step'):
        if funcion == 'step':
            return np.where(x > 0, 1, 0)
        if funcion == 'sigmoid':
            return 1 / (1 + np.exp(-x))
        if funcion == 'relu':
            return np.where(x > 0, x, 0)
        if funcion == 'tanh':
            return (np.exp(x) - np.exp(-x)) / (np.exp(x) + np.exp(-x))

#Funcion de prediccion del perceptron
    def predict(self, inputs):
        return self.act_fn(np.dot(inputs, self.weights[1:]) + self.weights[0])

#Funcion para entrenar el perceptron
    def train(self, in_train, clases, verbose=False):
        errores = []
        for _ in range(self.epochs):
            e = 0
            for entrada, clase in zip(in_train, clases):
                prediccion = self.predict(entrada)
                error = clase - prediccion
                self.weights[0] += self.lr * error
                self.weights[1:] += self.lr * error * entrada

                e += np.abs(error)
            errores.append(e)

        if verbose:
            plt.figure(), plt.plot(errores), plt.title('errores'), plt.show()
#Funcion para obtener los pesos del perceptron
    def get_weights(self):
        return self.weights

#Crear Arreglo para X y Y
x = np.array([[0,0], [0,1], [1,0], [1,1]])
y = np.array([0, 0, 0, 1])

#Crear una instancia de perceptron
perci = Perceptron(num_inputs=2, lr=0.2, epochs=10)

#Entrenamiento del perceptron
perci.train(x, y, True)

#Prediccion del perceptron
for entradas, label in zip(x, y):
  prediccion = perci.predict(entradas)

  print('entradas:', entradas, ' esperado:', label, ' predicción:', prediccion)