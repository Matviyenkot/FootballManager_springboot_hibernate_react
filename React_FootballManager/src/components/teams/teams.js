import {Table, Button} from "react-bootstrap";
import {useEffect, useState} from "react";
import Calls from "../service/calls";
import ModalWindow from "../modal/modal";
import ModalTransfer from "../modalTransfer/modalTransfer";


const Teams = () => {

    const calls = new Calls()

    const [teams, setTeams] = useState([])

    useEffect(() => {
        getAllTeams()
    }, [])



    async function _onUpdate(data) {
        const {name, finances, commission, id} = await data
        await calls.updateTeam({
            id,
            name,
            commission,
            finances,
        })
        await getAllTeams()
    }

    async function _onDelete(id) {
        await calls.deleteTeam(id)
        await getAllTeams()
    }

    async function _onAdd(data) {
        const {name, finances, commission} = await data
        await calls.addTeam({
            name,
            commission,
            finances,
        })
        await getAllTeams()
    }


    function getAllTeams() {
        calls.getAllTeams()
            .then(setTeams)
    }

    function renderTeams(teams) {
        return teams.map((team, teamKey) => {
            return <tr key={teamKey}>
                <td>{teamKey + 1}</td>
                <td>{team.name}</td>
                <td>{team.commission} %</td>
                <td>{team.finances}</td>
                <td>{team.players ? team.players.map(player =>'"' + player.name + '" ')  : '---'}</td>
                <td><Button onClick={() => _onDelete(team.id)} variant="danger">Delete</Button></td>
                <td><ModalWindow team={team} _onUpdate={_onUpdate}/></td>
                <td><ModalTransfer getAllTeams={getAllTeams} team={team} teamName={team.name} teams={teams}
                                   calls={calls}/></td>
            </tr>
        })
    }

    return (
        <>
            <ModalWindow _onAdd={_onAdd}/>
            <Table bordered hover>
                <thead>
                <tr>
                    <th>№</th>
                    <th>Name</th>
                    <th>Commission %</th>
                    <th>Finances</th>
                    <th>Players</th>
                    <th>Delete</th>
                    <th>Update</th>
                    <th>Transfer</th>

                </tr>
                </thead>
                <tbody>
                {renderTeams(teams)}
                </tbody>
            </Table>
        </>
    )
}
export default Teams