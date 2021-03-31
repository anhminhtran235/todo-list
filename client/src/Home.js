import { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import Todo from './components/Todo';
import alertify from 'alertifyjs';

const HomeStyle = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    form {
        margin-bottom: 20px;
    }
`;

const Home = () => {
    const [input, setInput] = useState({ task: '' });
    const [todos, setTodos] = useState({ tasks: [] });

    useEffect(() => {
        try {
            axios.get('/api/todos').then((res) => {
                setTodos({ tasks: res.data });
            });
        } catch (error) {}
    }, []);

    const onSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await axios.post('/api/todos', input.task, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            alertify.success('Added todo successfully');
            setTodos((prev) => ({
                tasks: [res.data, ...prev.tasks],
            }));
            setInput({ task: '' });
        } catch (error) {
            alertify.error('Failed to add todo');
            console.log(error);
        }
    };

    const onChange = (e) => {
        setInput({
            [e.target.name]: e.target.value,
        });
    };

    const deleteTodo = (id) => {
        axios
            .delete('api/todos/' + id)
            .then((res) => {
                alertify.success('Removed todo successfully');
                setTodos((prev) => {
                    const newTodos = prev.tasks.filter((todo) => todo.id != id);
                    setTodos({
                        tasks: newTodos,
                    });
                });
            })
            .catch((err) => {
                alertify.error('Failed to remove todo');
            });
    };
    return (
        <HomeStyle>
            <h1>Todo App</h1>
            <form onSubmit={onSubmit}>
                <input
                    type='text'
                    id='task'
                    placeholder='Enter your task'
                    name='task'
                    value={input.task}
                    onChange={onChange}
                    required
                />
                <button>Add todo</button>
            </form>
            {todos &&
                todos.tasks &&
                todos.tasks.length > 0 &&
                todos.tasks.map((task) => (
                    <Todo deleteTodo={deleteTodo} key={task.id} todo={task} />
                ))}
        </HomeStyle>
    );
};

export default Home;
