import {useEffect, useState} from "react";
import {Button, Modal} from "react-bootstrap";
import './modalTransfer.scss'

const ModalTransfer = (props) => {

    const [fullscreen, setFullscreen] = useState(true);
    const [show, setShow] = useState(false);
    const [players, setPlayers] = useState([])
    useEffect(() => {
        getPlayers()
    }, [])

    function getPlayers() {
        props.calls.playersGet().then(players => {
            setPlayers(players)
        })
    }

    async function _onTransfer(player) {
        await props.calls.transferPlayer(props.team.id, player.id)
        await props.getAllTeams()
        await getPlayers()
    }

    function _onSubmitFormAdd(e) {
        e.preventDefault()
    }

    function renderModalTransferContent() {
        return players.map(player => {
            const team = props.teams[props.teams.findIndex(team => team.id === player.team)]
            const checkCommand = team && props.teams[props.teams.findIndex(team => team.id === player.team)].name === props.teamName
            return <li key={player.id}
                       className={checkCommand ? "list-group-item disabled-text disabled" : "list-group-item"}>
                <div className="d-flex justify-content-around">
                    <div>{player.name}</div>
                    <div><b>Team: </b>{
                        props.teams.map(team => {
                            if (team.id === player.team) {
                                return <span key={team.id}>{team.name}</span>
                            }
                        })
                    }</div>
                    <button onClick={() => _onTransfer(player)} type="button"
                            className={checkCommand ? "btn btn-primary disabled disabled-text" : "btn btn-primary "}>Transfer
                    </button>

                </div>
            </li>
        })
    }


    return (
        <>
            <Button className="me-2 mb-2" onClick={() => {
                setFullscreen('md-down');
                setShow(true);
            }}>
                Transfer
            </Button>
            <Modal show={show} fullscreen={fullscreen} onHide={() => setShow(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Transfer</Modal.Title>
                </Modal.Header>
                {/*<form className="p-3" onSubmit={_onSubmitFormAdd}>*/}
                <form className="p-3" onSubmit={_onSubmitFormAdd}>
                    <ul className="list-group pb-3">
                        {renderModalTransferContent()}
                    </ul>
                    {/*<button className="btn btn-primary">Submit</button>*/}
                </form>
            </Modal>
        </>
    );
}
export default ModalTransfer