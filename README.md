# Task Manager

just needed a manager app to arrange my tasks so i rather to do it myself instead of downloading an application.


## Features

1- add a task.

2- get tasks by month.

3- get date for a task.

4- get remaining days for a task.

5- an endpoint for when a user finished a task(set the position to done).


## API Reference

#### Add a Task

```http
  POST /task/add
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `UserDto` | `UserDTO` | **Required**. Your task details |

#### Get task by month

```http
  GET /task/in/${month}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `month-name`      | `string` | **Required**. name of the month |

#### Get date of a task

```http
  GET /task/date/${task}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `task-name`      | `string` | **Required**. name of the task |

#### Get remaining days of a task

```http
  GET /task/days/to/${task}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `task-name`      | `string` | **Required**. name of the task |


#### PUT set position to completed

```http
  PUT /task/done/${task}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `task-name`      | `string` | **Required**. name of the task |

## Run Locally

Clone the project

```bash
  git clone https://github.com/Fatemehjj/Task-Manager.git
```

Go to the project directory

make sure maven installed and correctly added to your environment variable

also make sure to configure your database in application.properties

```bash
    mvn spring-boot:run
```



## License

[MIT](https://choosealicense.com/licenses/mit/)

