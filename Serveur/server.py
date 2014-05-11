from flask import Flask, request, Response, jsonify
from functools import wraps
from uuid import uuid4
import hashlib
import zlib
import json
import os

######################################################################
#                                                                   #
#                         Functions                                 #
#                                                                   #
######################################################################

def check_username(username):
    return True

def check_password(username, password):
    return True

def check_auth(username,  password):
    return check_username(username) and check_password(username, password)

def generate_token():
    sign = hashlib.sha256()
    sign.update(str(uuid4()))
    token = sign.hexdigest()
    return token +  '/' + str(zlib.crc32(token))



def validate_token(token):
    sign, checksum = token.strip().split('/')
    verif = zlib.crc32(sign)
    return int(verif) == int(checksum)

def requires_token(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        auth = request.headers['Authorization']
        if auth is not None and 'Bearer' in auth:
            parts = auth.split(' ')
            if len(parts) >= 2 and validate_token(parts[1]):
                return func(*args, **kwargs)
        return Response('Please, login with porper credentials', 401, {'WWW-Authenticate': 'Basic realm="Login Required"'})
    return wrapper

def load_talks(file):
    talks = json.load(open(file))
    return dict([(talk['id'], {'title': talk['title'], 'description': talk['description']}) for talk in talks])

######################################################################
#                                                                    #
#                         Application                                #
#                                                                    #
######################################################################

talks = load_talks(os.environ.get('TALKS_FILE', './talks.json'))
app = Flask(__name__)

@app.route("/", methods=["GET"])
def index():
    return "Bienvenue sur le programme du BreizhCamp"

@app.route("/auth/login", methods=["GET"])
def login():
    auth = request.authorization
    if check_auth(auth.username, auth.password):
        token = generate_token()
        return jsonify(token=token)
    else:
        return Response('Access denied', 403)

@app.route("/auth/logout", methods=["GET"])
def logout():
    return Response('Done', 200)

@app.route("/order", methods=["POST"])
@requires_token
def order():
    return Response('Done', 200)

@app.route("/talk/<talk_id>", methods=["GET"])
def talk(talk_id):
    id = -1
    try:
        id = int(talk_id)
        if id not in talks.keys():
            raise 'Invalid talk id'
        return jsonify(talks[id])
    except:
        return Response('Invalid talk id', 400)

if __name__ == "__main__":
    app.run(debug=True)
