import socket
import random


HOST = '127.0.0.1'
PORT = 4000


opcoesJogadas = [0, 1, 2, 3, 4]

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)


sock.connect((HOST, PORT))

while True:
    i=0
    while i<15:
        mensagemEnvioClient = (random.choice(opcoesJogadas)*3 + 1) % 5
        print(i)
        print("->Palpite escolhido: ", mensagemEnvioClient)
        sock.sendall(bytes(str(mensagemEnvioClient), 'utf-8'))
        print("Palpite enviado pelo cliente: ", mensagemEnvioClient)
        data = sock.recv(1024)
        print('Palpite do servidor: ', int.from_bytes(data, "big"))
        i = i + 1


    sock.close()