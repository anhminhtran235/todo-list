import styled from 'styled-components';
import axios from 'axios';

const TodoStyle = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px 30px;
    margin-top: 10px;
    border: 1px solid grey;
    border-radius: 3px;
    background: grey;
    min-width: 300px;
    color: white;
    font-size: 20px;
    p {
        margin: 0;
    }
    .delete-btn {
        :hover {
            cursor: pointer;
        }
        line-height: 1;
    }
`;

const Todo = ({ deleteTodo, todo }) => {
    return (
        <TodoStyle>
            <p>{todo.description}</p>
            <span className='delete-btn' onClick={() => deleteTodo(todo.id)}>
                x
            </span>
        </TodoStyle>
    );
};

export default Todo;
