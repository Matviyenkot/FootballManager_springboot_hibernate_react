class Calls {
    _apiBase = 'http://localhost:8080/teams';
    _apiBasePlayers = 'http://localhost:8080/players';

    getResource = async (url) => {
        let res = await fetch(url);

        if (!res.ok) {
            throw new Error(`Could not fetch ${url}, status: ${res.status}`);
        }

        return await res.json();
    }

    getAllTeams = async () => {
        return await this.getResource(`${this._apiBase}/get`);
    }

    addTeam = async (data) => {
        const response = await fetch(`${this._apiBase}/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        return await response.json();
    }

    updateTeam = async (data,id) => {
        const response = await fetch(`${this._apiBase}/update`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        return await response.json();
    }

    deleteTeam = async (id) => {
        return await fetch(`${this._apiBase}/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });

    }

    playersGet = async () => {

        return await this.getResource(`${this._apiBasePlayers}/get`);

        // const response =
        //     await fetch(`${this._apiBasePlayers}/get`, {
        //     method: 'GET',
        //     mode: "no-cors",
        //     headers: {
        //         'Content-Type': 'application/json'
        //     }
        // });
        // return await response.json();
    }


    transferPlayer = async (teamId,playerId) => {
     await fetch(`${this._apiBase}/${teamId}/players/${playerId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }


}

export default Calls;