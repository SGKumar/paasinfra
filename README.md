# Problem statement

Given a schedule (start, end, bandwidth) find the maximum bandwidth during the day?
# Input
Details and how to test in `How To Test` file.

# Solution Description
2 general approaches provided, both take **O(nlogn)** time
## Assumptions:
* If channel-1's end-time = channel-2's start-time, assume **NO** overlap
The test file is assumed to be valid with the various values given for tests/channels.
## Approach-1 (Solution2.java):
Given this input,
```
800, 1200, 4
600, 800, 6
900, 1400, 5
1500, 1600, 11
```
### Step 1: 
Have 2 tables, sorted in ascending order by the epoch-times (start & end)
* If 2 epoch-times are the same, sort in ascending order by bandwidth

Most efficient sorting takes **O(nlogn)** time

Input looks like this now:
<table>
<tr><th>Epoch-begin</th><th>Epoch-end</th></tr>
<tr><td>

| Epoch | Bandwidth |
| ----: | --------: |
| 600   | 6         |
| 800   | 4         |
| 900   | 5         |
| 1500  | 11        |

</td><td>

| Epoch | Bandwidth |
| ----: | --------: |
| 800   | 6         |
| 1200  | 4         |
| 1400  | 5         |
| 1600  | 11        |

</td></tr> </table>

### Step 2: 
Figure sliding windows including each channel from the beginning.
All channels starting with
* `Channel-1`: just 1 item in window as (2) begins after (1) ends, bandwidth = 6
* `Channel-2`: 2 items (2) & (3) in window, bandwidth = 9
* `Channel-3`: just 1 item in window as (4) begins after (3) ends, bandwidth = 5
* `Channel-4`: just 1 item in window as (4) is the end, bandwidth = 11

This processing takes **O(n)** time
### Step 3: 
Running maximum from above is **`11`**
> Approach-1 takes **O(nlogn)** time, space complexity is **O(n)**
## Approach-2 (Solution3.java):
Given this input,
```
800, 1200, 4
600, 800, 6
900, 1400, 5
1500, 1600, 11
```
### Step 1:
Specify above data as below :
* For start-times bandwidth is the positive value
* For end-times bandwidth is a negative value

| Epoch | Bandwidth |
| ----: | --------: |
| 800   | 4         |
| 1200  | -4        |
| 600   | 6         |
| 800   | -6        |
| 900   | 5         |
| 1400  | -5        |
| 1500  | 11        |
| 1600  | -11       |

### Step 2:
Sort in ascending order by epoch (column 1 from Step-1)
* If 2 epochs are the same, negative bandwidth value takes precedence over positive bandwidth value.

Table looks as below:

| Epoch | Bandwidth |
| ----: | --------: |
| 600   | 6         |
| 800   | -6        |
| 800   | 4         |
| 900   | 5         |
| 1200  | -4        |
| 1400  | -5        |
| 1500  | 11        |
| 1600  | -11       |

Most efficient sorting takes **O(nlogn)** time
### Step 3:
Calculate running (maximum bandwidth) as below:

| Epoch | Bandwidth | Bandwidth |
| ----: | --------: | --------: |
| 600   | 6         | 6         |
| 800   | -6        | 0         |
| 800   | 4         | 4         |
| 900   | 5         | 9         |
| 1200  | -4        | 5         |
| 1400  | -5        | 0         |
| 1500  | 11        | 11        |
| 1600  | -11       | 0         |

This step takes **O(n)** time, maximum bandwidth from above is **`11`**

> Approach-2 takes **O(nlogn)** time, space complexity is **O(n)**

