import './App.scss';
import {Container, Table} from "react-bootstrap";
import Header from "../header/header";
import Teams from "../teams/teams";

function App() {
    return (
        <Container>
            <Header/>
            <Teams/>
        </Container>
    );
}

export default App;
