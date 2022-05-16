import {useState} from "react";
import {Button, Modal} from "react-bootstrap";

const ModalWindow = (props) => {

    const [fullscreen, setFullscreen] = useState(true);
    const [show, setShow] = useState(false);
    const [name, setName] = useState(props.team ? props.team.name : '');
    const [commission, setCommission] = useState(props.team ? props.team.commission : '');
    const [finances, setFinances] = useState(props.team ? props.team.finances : '');

    function _onSubmitFormAdd(e) {
        e.preventDefault()
        const createdOb = {
            name,
            commission,
            finances
        }
        if (props.team) createdOb.id = props.team.id
        if (props._onAdd) {
            props._onAdd(createdOb)
            setName('')
            setCommission('')
            setFinances('')
        } else {
            props._onUpdate(createdOb)
        }
        setShow(false)
    }

    function commissionValid(data){
        if(data >= 0 && data <= 10){
            setCommission(data)
        }
        else{
            console.log("Invalid Commission")
        }
    }

    function financesValid(data){
        if(data > 0){
            setFinances(data)
        }
        else{
            console.log("Invalid Finances")
        }
    }

    return (
        <>
            <Button className="me-2 mb-2" onClick={() => {
                setFullscreen('md-down');
                setShow(true);
            }}>
                {props._onUpdate ? 'Update' : 'Add Team'}
            </Button>
            <Modal show={show} fullscreen={fullscreen} onHide={() => setShow(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Add</Modal.Title>
                </Modal.Header>
                <form className="p-3" onSubmit={_onSubmitFormAdd}>
                    <div className="mb-3">
                        <label htmlFor="name" className="form-label">Name</label>
                        <input required value={name} onChange={(e) => setName(e.target.value)} type="text"
                               className="form-control" id="name"
                        />
                        <label htmlFor="commission" className="form-label">Commission</label>
                        <div>Commission should be from 0 to 10 %</div>
                        <input required value={commission} onChange={(e) => commissionValid(e.target.value)} type="number"
                               className="form-control"
                               id="commission"
                        />
                        <label htmlFor="finances" className="form-label">Finances</label>
                        <div>Finances should be more then 1</div>
                        <input required value={finances} onChange={(e) => financesValid(e.target.value)} type="number"
                               className="form-control"
                               id="finances"
                        />
                    </div>
                    <button className="btn btn-primary">Submit</button>
                </form>
            </Modal>
        </>
    );
}
export default ModalWindow